package com.coachqa.ws.controllor;


import com.coachqa.entity.AppUser;
import com.coachqa.entity.Classroom;
import com.coachqa.repository.dao.impl.QuestionQueryBuilder;
import com.coachqa.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class QueryCriteriaTest {

    public static void run(String[] args) {
        QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder("question", "q");
        String query = queryBuilder
                .withSelectCols("q", Arrays.asList(new String[]{"questionid","refsubjectid","questionlevelid","refquestionstatusid","title","lastactivedate","ispublic"}))
                .withSelectCols("p", Arrays.asList(new String[]{"votes","postedby","content","postdate"}))
                .withSelectCols("u", Arrays.asList(new String[]{"firstname","middlename","lastName"}))
                .withJoin("appuser", "u", "appuserId", "p", "postedby", 2)
                .withJoin("post", "p", "postId", "q", "questionid", 1)
                .withJoin("questiontag", "qt", "questionid", "q", "questionid", 3)
                .withSubject(1)
                .withClassroom(new Classroom( 1) )
                .withTag(Arrays.asList(new Integer[]{1, 2}))
                .withPostedByUser(new AppUser(1, "", "", "", ""))
                .withOderBy("p", "postdate", QuestionQueryBuilder.ORDER.DESC)
                .withLimit(0, 5)
                .buildQuery();

        System.out.println(query);


    }

    public static void main1(String[] args) {
        QuestionQueryBuilder queryBuilder = new QuestionQueryBuilder("question", "q");

        QueryCriteria q = QueryCriteria.sampleQuery;

        queryBuilder
                = queryBuilder
                .withSelectCols("q", Arrays.asList(new String[]{"questionid","refsubjectid","questionlevelid","refquestionstatusid","title","lastactivedate","ispublic"}))
                .withSelectCols("p", Arrays.asList(new String[]{"votes","postedby","content","postdate", "noofviews", "posttype", "classroomid", "approvalstatus"}))
                .withSelectCols("u", Arrays.asList(new String[]{"firstname","middlename","lastName"}))
                .withJoin("appuser", "u", "appuserId", "p", "postedby", 2)
                .withJoin("post", "p", "postId", "q", "questionid", 1)
                .withJoin("refsubject", "s", "refsubjectid", "q", "refsubjectid" , 3);
//				.withJoin("questiontag", "qt", "questionid", "q", "questionid", 4)
//				.withJoin("tag", "t", "tagid","q", "tagid", 5);


        if(!CollectionUtils.isEmpty(q.getSubject())) {
            queryBuilder.withCondition(new QuestionQueryBuilder.QueryCondition<String>("s.subjectname", q.getSubject())
                    .withJoinType(QuestionQueryBuilder.JoinTypeEnum.IN));
        }

        if(!CollectionUtils.isEmpty(q.getTag())) {
            queryBuilder.withTagNameCondition(q.getTag());
        }


        if(!CollectionUtils.isEmpty(q.getPostedBy())) {
            queryBuilder.withCondition(new QuestionQueryBuilder.QueryCondition<String>("u.firstname", q.getPostedBy()).
                    withJoinType(QuestionQueryBuilder.JoinTypeEnum.IN));
        }

        if(!q.getSimpleParam().isEmpty()) {
            queryBuilder.withCondition(new QuestionQueryBuilder.QueryCondition<String>("title", q.getSimpleParam())
                    .withJoinType(QuestionQueryBuilder.JoinTypeEnum.LIKE));
        }

        queryBuilder
                .withApprovedOnly()
                .withOderBy("p", "postdate", QuestionQueryBuilder.ORDER.DESC);
//                .withLimit(page, noOfPaginatedResults);

        String query = queryBuilder.buildQuery();

        System.out.println(query);


    }

    public static void main(String[] args) {
//        String query = "question &postedby:bla&postedby:bla1&subject:bla&subject:bla subject&tag:bla&tag:bla tag &" +
//                "classroom:bla& answer";

//        String query = "qu postedby:anand tag:\"java script\" aer subject:physics tag classroom:\"ph es\"" ;
        // TODO: 28/04/18 introduce a space after closing quote " if not present
        String query1 = "postedby:\"anand nigam tag:javas cript tag:\"java script subject:physics classroom:\"ph " +
                "es\" testing:answer end" ;
        String query2 = "tag:\"java script\" postedby:anand subject:physics classroom:\"ph es\" testing" ;

        Set<String> vt = new HashSet(Arrays.asList(new String[]{"postedby", "tag", "classroom", "subject", "gen"}));
        Map<String, String> keyVal = new HashMap<>();
        keyVal.put("gen", "");
        for(String s : query1.split("\" ")) {


            s = s.replace("\"", "");
//            System.out.println(s);

//            System.out.println("----------------------------------------------------------");

            String key="gen";
            String val;
            for  ( String a : s.split(" ")) {
                String[] b = a.split(":");
                if(b.length == 2){

                    key = b[0];
                    if(vt.contains(key)) {
                        val = b[1];
                        // TODO: 28/04/18 if a tag is repeated then its values gets overridden
                        keyVal.put(key, val);
                    }
                    else {
                        val = keyVal.get("gen")+" "+ a;
                        keyVal.put("gen", val);
                        key = "gen";
                    }
                }
                else if(b.length == 1){
                    val = keyVal.get(key)+" "+ a;
                    keyVal.put(key, val);
                }

            }
//            System.out.println("__________________________________________________________");

        }
        for( Map.Entry e : keyVal.entrySet()) {
            System.out.println(e.getKey()+"="+e.getValue());
        }




    }

}
