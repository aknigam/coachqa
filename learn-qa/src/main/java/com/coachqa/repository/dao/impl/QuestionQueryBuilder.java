package com.coachqa.repository.dao.impl;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.enums.QuestionLevelEnum;
import com.coachqa.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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

    public QuestionQueryBuilder withClassroom(Classroom classroom) {
        if(classroom != null && classroom.getClassroomId() !=  null) {
            conditions.add(new QueryCondition<Integer>("classroomid", classroom.getClassroomId()));
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
    @Deprecated
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
                .onJoinCondition(new QueryCondition<String>(targetTableAlias+"."+targetCol, joinTableALias+"."+col,
                        ConditionType.COLUMN )
                        .withJoinType(JoinTypeEnum.EQUALS)));
        return this;
    }

    public QuestionQueryBuilder withApprovedOnly() {
        //  0 means approved questions
        conditions.add(new QueryCondition("approvalstatus", 1).withJoinType(JoinTypeEnum.EQUALS));
        return this;
    }

    public void withSubjectCondition(List<Integer> tags) {
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

    public void withTagNameCondition(List<String> tags) {
        StringBuilder tagSelectQuery = new StringBuilder(" SELECT qt.questionid from questiontag qt " +
                " JOIN tag t on t.TagId = qt.TagId where tagname in (");

        int size = tags.size();
        int i =1;
        for (String tagId:tags) {
            tagSelectQuery.append(getVal(tagId, ConditionType.VALUE));
            if(i++ != size) {
                tagSelectQuery.append(",");
            }
        }
        tagSelectQuery.append(" ) ");

        conditions.add(new QueryCondition("questionid", "( "+tagSelectQuery+" ) ").withJoinType(JoinTypeEnum.IN_SUBQUERY));
    }
    public void withTagsCondition(List<Integer> tags) {
        StringBuilder tagSelectQuery = new StringBuilder(" SELECT qt.questionid from questiontag qt where tagid in (");

        int size = tags.size();
        int i =1;
        for (Integer tagId:tags) {
            tagSelectQuery.append(getVal(tagId, ConditionType.VALUE));
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

    public void withCondition(QueryCondition condition) {
        conditions.add(condition);
    }

    public static <T> String getVal(T rhs, ConditionType conditionType) {
        if(conditionType == ConditionType.COLUMN){
            return rhs.toString();
        }
        if(rhs instanceof Integer){
            return rhs.toString();
        }
        else if(rhs instanceof String) {
            return "'"+rhs+"'";
        }
        return rhs.toString();
    }

    public QuestionQueryBuilder withAccountId(int accountId) {
        conditions.add(new QueryCondition("u.accountId", accountId).withJoinType(JoinTypeEnum.EQUALS));
        return this;
    }

    public static class QueryCondition<T>{


        private static String AND = "and";
        private static String OR = "or";

        private final String lhs;
        private final T rhs;
        private  ConditionType conditionType = ConditionType.VALUE;
        private Collection<T> vals;
        private JoinTypeEnum joinType = JoinTypeEnum.EQUALS;

        public QueryCondition(String lhs , T rhs){
            this.lhs =  lhs;
            this.rhs = rhs;
        }
        public QueryCondition(String lhs , Collection<T> rhs){
            this.lhs =  lhs;
            this.vals = rhs;
            this.rhs = null;
        }

        public QueryCondition(String lhs, T rhs, ConditionType conditionType) {
            this(lhs, rhs);
            this.conditionType = conditionType;
        }

        public String getOrCondition(){
            return " "+ OR + " " + lhs +" = " + rhs;
        }

        public String getAndCondition(){
            switch (joinType){
                case EQUALS:
                    return " "+ lhs +" = " + getVal(rhs, conditionType);
                case IN:
                    if(!CollectionUtils.isEmpty(vals)){
                        StringBuilder q = new StringBuilder(  lhs +" in (");
                        int i = 0;
                        for(T val : vals){
                            if(i++ == (vals.size() -1))
                                q.append(getVal(val, conditionType)).append(")");
                            else
                                q.append(getVal(val, conditionType)).append(",");
                        }
                        return q.toString();
                    }

                case IN_SUBQUERY:
                    return lhs + " in "+ rhs;
                case LIKE:
                    return " "+ lhs +" like '%" + rhs+"%' ";
                default:
                    return "";
            }
        }




        public QueryCondition withJoinType(JoinTypeEnum joinType) {
            this.joinType =  joinType;
            return this;
        }
    }



    public static enum ConditionType{
        VALUE, COLUMN

    }
    public static enum JoinTypeEnum{
        EQUALS,
        IN,
        IN_SUBQUERY,
        LIKE
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

    public static enum ORDER{
        ASC, DESC
    }



}
