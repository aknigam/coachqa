package com.coachqa.repository.dao.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.enums.QuestionLevelEnum;
import com.coachqa.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class QuestionQueryBuilder {


    private static final String SPACE = " ";
    private static final String DOT = ".";
    private static Logger LOGGER = LoggerFactory.getLogger(QuestionQueryBuilder.class);

    private Boolean mostViewed = null;
    private final String alias;
    private String tableName ;

    private List<String> selectCols = new ArrayList<>();
    private List<QueryCondition> conditions = new ArrayList<>();
    private List<JoinTable> joins = new ArrayList<>();
    private List<String> orderBy = new ArrayList<>();
    private int firstResultsRow;
    private int noOfResults;

    public QuestionQueryBuilder(String table, String alias){
        tableName = table;
        this.alias = alias;
    }

    public String buildQuery() {

        StringBuilder query = new StringBuilder();

        query.append("SELECT").append("\n");
        int j = 0;
        for (String col : selectCols){
            if(j++ == selectCols.size() -1)
                query.append(col).append("\n");
            else
                query.append(col).append(",").append("\n");
        }


        this.joins= new ArrayList<JoinTable>(new HashSet<>(this.joins));
        Collections.sort(this.joins);
        query.append("From").append(SPACE).append(tableName).append(" ").append(alias).append(" ").append("\n");
        for(JoinTable jt : this.joins){
            query.append("JOIN").append(SPACE).append(jt.tableName).append(SPACE).append(jt.alias)
                    .append(SPACE).append("ON").append(SPACE).append(jt.joinCondition.getAndCondition())
                    .append("\n");
        }
        if(CollectionUtils.isEmpty(conditions)){
            return query.toString();
        }
        query.append("WHERE ");
        int i = 0;
        for(QueryCondition qc: conditions){
            if(i++  == 0)
                query.append(" ").append(qc.getAndCondition()).append("\n");
            else
                query.append(" AND ").append(qc.getAndCondition()).append("\n");
        }

        for(String orderByClause: orderBy){
            query.append(" order by "+orderByClause).append("\n");
        }

        query.append(" LIMIT "+ firstResultsRow+" , "+ noOfResults);

        return query.toString();
    }


    public QuestionQueryBuilder withSubject(Integer refSubjectId) {
        if(refSubjectId != null && refSubjectId != 0)
            conditions.add(new QueryCondition<Integer>("refsubjectid", refSubjectId));
        return this;
    }

    public QuestionQueryBuilder withClassroom(Integer classroomId) {
        if(classroomId!= null ) {
            conditions.add(new QueryCondition<Integer>("classroomid", classroomId));
        }else{
            LOGGER.debug("classroom condition not added");
        }
        return this;
    }

    public QuestionQueryBuilder withTag(List<Integer> tagId) {
        if(CollectionUtils.isEmpty(tagId)){
            return this;
        }
        conditions.add(new QueryCondition<Integer>("tagid", tagId.get(0)).withJoinType(JoinTypeEnum.EQUALS));
        return this;
    }


    public QuestionQueryBuilder withDateRange() {
        return this;
    }

    public QuestionQueryBuilder withMostViewed() {
        mostViewed  = true;
        return this;
    }

    public QuestionQueryBuilder withDifficultyLevel(QuestionLevelEnum questionLevelEnum) {
        return this;
    }

    public QuestionQueryBuilder withAnsweredQuestionsOnly() {
        return this;
    }

    public QuestionQueryBuilder withMostActiveQuestions() {
        return this;
    }

    public QuestionQueryBuilder withPostedByUser(AppUser postedBy) {
        if(postedBy == null)
            return this;


        conditions.add(new QueryCondition<Integer>("postedby", postedBy.getAppUserId()).withJoinType(JoinTypeEnum.EQUALS));
        return this;
    }

    public QuestionQueryBuilder withPublicOnly(Boolean aPublic) {
        if(aPublic == null)
            return this;
        conditions.add(new QueryCondition<Integer>("ispublic", aPublic? 1: 0).withJoinType(JoinTypeEnum.EQUALS));
        return this;
    }


    public QuestionQueryBuilder withSelectCols(String alias, List<String> selects) {
        if(CollectionUtils.isEmpty(selects)){
            return this;
        }
        for (String col : selects){
            selectCols.add(alias+"."+col);
        }
        return this;
    }

    public QuestionQueryBuilder withJoin(String joinTableName, String joinTableALias, String col, String targetTableAlias, String targetCol, int joinOrder) {
        joins.add(new JoinTable(joinTableName, joinTableALias, joinOrder)
                .onJoinCondition(new QueryCondition<String>(targetTableAlias+"."+targetCol, joinTableALias+"."+col)
                        .withJoinType(JoinTypeEnum.EQUALS)));
        return this;
    }

    public QuestionQueryBuilder withApprovedOnly() {
        //  0 means approved questions
        conditions.add(new QueryCondition("approvalstatus", 1).withJoinType(JoinTypeEnum.EQUALS));
        return this;
    }

    public void withTagsCondition(List<Integer> tags) {
        StringBuilder tagSelectQuery = new StringBuilder(" SELECT qt.questionid from questiontag qt where tagid in (");

        int size = tags.size();
        int i =1;
        for (Integer tagId:tags) {
            tagSelectQuery.append(tagId);
            if(i++ != size) {
                tagSelectQuery.append(",");
            }
        }
        tagSelectQuery.append(" ) ");

        conditions.add(new QueryCondition("questionid", "( "+tagSelectQuery+" ) ").withJoinType(JoinTypeEnum.IN_SUBQUERY));
    }

    public QuestionQueryBuilder withOderBy(String alias, String column, ORDER desc) {
        orderBy.add(alias+"."+column +" " + desc);
        return this;

    }

    public QuestionQueryBuilder withLimit(int page, int noOfResults) {
        this.firstResultsRow =   page* noOfResults;
        this.noOfResults = noOfResults;
        return this;
    }


    static class QueryCondition<T>{


        private static String AND = "and";
        private static String OR = "or";

        private final String lhs;
        private final T rhs;
        private Collection<T> vals;
        private JoinTypeEnum joinType = JoinTypeEnum.EQUALS;

        public QueryCondition(String lhs , T rhs){
            this.lhs =  lhs;
            this.rhs = rhs;
        }

        public String getOrCondition(){
            return " "+ OR + " " + lhs +" = " + rhs;
        }

        public String getAndCondition(){
            switch (joinType){
                case EQUALS:
                    return " "+ lhs +" = " + rhs;
                case IN:
                    if(CollectionUtils.isEmpty(vals)){
                        StringBuilder q = new StringBuilder( " "+ AND + " " + lhs +" in (");
                        int i = 0;
                        for(T val : vals){
                            if(i == vals.size() -1)
                                q.append(val.toString()).append(")");
                            else
                                q.append(val.toString()).append(",");
                        }
                        return q.toString();
                    }

                case IN_SUBQUERY:
                    return lhs + " in "+ rhs;
                default:
                    return "";
            }
        }

        public QueryCondition withJoinType(JoinTypeEnum joinType) {
            this.joinType =  joinType;
            return this;
        }
    }

    static class QueryConditionBuilder{

    }

    static enum JoinTypeEnum{
        EQUALS,
        IN,
        IN_SUBQUERY
    }

    static class JoinTable implements Comparable<JoinTable>{

        private final String alias;
        private final int joinOrder;
        private String tableName ;
        private QueryCondition joinCondition;

        public JoinTable(String table, String alias, int order){
            tableName = table;
            this.alias = alias;
            this.joinOrder = order;
        }

        public JoinTable onJoinCondition(QueryCondition joinCondition) {
            this.joinCondition = joinCondition;
            return this;
        }

        @Override
        public String toString() {
            return alias+"."+tableName+"-"+ joinOrder;
        }

        @Override
        public int compareTo(JoinTable o) {
            if(joinOrder > o.joinOrder){
                return 1;
            }
            if(joinOrder < o.joinOrder){
                return -1;
            }
            else{
                return 0;
            }

        }
    }

    static enum ORDER{
        ASC, DESC
    }

    public static void main(String[] args) {
        QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder("question", "q");
        String query = queryBuilder
                .withSelectCols("q", Arrays.asList(new String[]{"questionid","refsubjectid","questionlevelid","refquestionstatusid","title","lastactivedate","ispublic"}))
                .withSelectCols("p", Arrays.asList(new String[]{"votes","postedby","content","postdate"}))
                .withSelectCols("u", Arrays.asList(new String[]{"firstname","middlename","lastName"}))
                .withJoin("appuser", "u", "appuserId", "p", "postedby", 2)
                .withJoin("post", "p", "postId", "q", "questionid", 1)
                .withJoin("questiontag", "qt", "questionid", "q", "questionid", 3)
                .withSubject(1)
                .withClassroom(1)
                .withTag(Arrays.asList(new Integer[]{1, 2}))
                .withPostedByUser(new AppUser(1, "", "", "", ""))
                .withPublicOnly(true)
                .withOderBy("p", "postdate", QuestionQueryBuilder.ORDER.DESC)
                .withLimit(0, 5)
                .buildQuery();

        System.out.println(query);


    }
}
