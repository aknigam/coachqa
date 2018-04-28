package com.coachqa.ws.controllor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class QueryCriteria {

    enum QueryParam  {
        postedby,
        subject,
        tag,
        classroom;
    }

    private List<String> postedBy = new ArrayList<>();
    private List<String> classroom = new ArrayList<>();
    private List<String> tag= new ArrayList<>();
    private List<String> subject= new ArrayList<>();
    private StringBuilder simpleParam= new StringBuilder();

    public void setPostedBy(String postedBy) {
        this.postedBy.add( postedBy );
    }

    public void setClassroom(String classroom) {
        this.classroom.add(classroom);
    }

    public void setTag(String tag) {
        this.tag.add(tag);
    }

    public void setSubject(String subject) {
        this.subject.add(subject);
    }


    public void addQueryParams(String key, String val) {
        if (key.equals(QueryParam.postedby.name())) {
            setPostedBy(val);
        }
        else if (key.equals(QueryParam.subject.name())) {
            setSubject(val);
        }
        else if (key.equals(QueryParam.tag.name())) {
            setTag(val);
        }
        else if (key.equals(QueryParam.classroom.name())) {
            setClassroom(val);
        }
        else{
            addSimpleQueryParam(key+":"+val);
        }
    }

    public void addSimpleQueryParam(String s) {
        this.simpleParam.append(s);
    }


    public List<String> getPostedBy() {
        return postedBy;
    }

    public List<String> getClassroom() {
        return classroom;
    }

    public List<String> getTag() {
        return tag;
    }

    public List<String> getSubject() {
        return subject;
    }

    public String getSimpleParam() {
        return simpleParam.toString().trim();
    }



    public static QueryCriteria sampleQuery = QueryCriteria.parseQuery(
            "question &postedby:bla&postedby:bla1&subject:bla&subject:bla subject&tag:bla&tag:bla tag &" +
                    "classroom:bla& anser "
    );


    public static QueryCriteria parseQuery1(String query) {




        return null;
    }

    public static QueryCriteria parseQuery(String query) {

        QueryCriteria c = new QueryCriteria();

        Map<String, String> map = new HashMap<>();

        StringBuilder gen = new StringBuilder();


        String[] s = query.split("&");
        for( String k : s ) {
            String[] y = k.split(":");

            if(y.length == 2) {
                c.addQueryParams(y[0], y[1]);
            }
            else {
                gen.append(k).append(" ");
            }

        }
        c.addSimpleQueryParam(gen.toString());
        return c;
    }
    @Override
    public String toString() {
        return QueryParam.postedby +"\t:"+ postedBy +"\n"+
                QueryParam.subject +"\t:"+ subject +"\n"+
                QueryParam.tag +"\t:"+ tag +"\n"+
                QueryParam.classroom +"\t:"+ classroom +"\n"+
                "Simple params" +"\t:"+ simpleParam +"\n";
    }


}
