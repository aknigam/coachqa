package com.coachqa.service;


import com.coachqa.entity.Question;
import com.coachqa.entity.Tag;
import com.coachqa.service.impl.QuestionServiceImpl;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Refer: http://lucene.apache.org/solr/guide/7_6/using-solrj.html
 */
@Component
public class SolrIndex implements IndexService{

    private static Logger LOGGER = LoggerFactory.getLogger(SolrIndex.class);

    private static String COLLECTION_NAME = "learnqa";

    final String node1SolrUrl = "http://localhost:7475/solr";
    final String node2SolrUrl = "http://localhost:8983/solr";

    private SolrClient client;

    @Autowired
    private QuestionService questionService;

    @PostConstruct
    public void init() {
//        final String solrUrl = "http://localhost:8983/solr";
//        client  =  new HttpSolrClient.Builder(solrUrl)
//                .withConnectionTimeout(10000)
//                .withSocketTimeout(60000)
//                .build();

        List<String> urls = Arrays.asList(node1SolrUrl, node2SolrUrl);
        client = new CloudSolrClient.Builder(urls)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
    }

    @Override
    public void updatePostExtractedtext(Integer postId, String imageExtractedText) {
        Question q = questionService.getQuestionById(postId);
        final SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id", postId);
        doc.addField("content", imageExtractedText);
        StringBuilder tags = new StringBuilder();
        q.getTags().stream().map(t -> tags.append(t.getTagName()).append(" ")).count();
        doc.addField("tags", tags);
        doc.addField("classroom", q.getClassroom().getClassName());
        doc.addField("subject", q.getSubject().getSubjectName());

        try {
            final UpdateResponse updateResponse = client.add(COLLECTION_NAME, doc);
            // Indexed documents must be committed
            client.commit(COLLECTION_NAME);
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SolrIndex index = new SolrIndex();
        index.init();
//        index.updatePostExtractedtext(100, "this is the first question to be indexed. " +
//                "This is a math question");


        Question q = new Question();
        q.setQuestionId(100);
        System.out.println(index.search(q));

    }


    /*

    queryBuilder
		 = queryBuilder
				.withSelectCols("q", Arrays.asList(new String[]{"questionid","refsubjectid","questionlevelid","refquestionstatusid","title","lastactivedate"}))
				.withSelectCols("p", Arrays.asList(new String[]{"votes","postedby","content","postdate", "noofviews", "posttype", "classroomid", "approvalstatus"}))
				.withSelectCols("u", Arrays.asList(new String[]{"firstname","middlename","lastName"}))
				.withSelectCols("c", Arrays.asList(new String[]{"ClassName"}))
				.withSelectCols("s", Arrays.asList(new String[]{"subjectname"}))
				.withJoin("appuser", "u", "appuserId", "p", "postedby", 2)
				.withJoin("post", "p", "postId", "q", "questionid", 1)
				.withJoin("classroom", "c", "classroomid", "p", "classroomid", 3)
				.withJoin("refsubject", "s", "refsubjectid", "q", "refsubjectid", 4)
				.withSubject(q.getRefSubjectId())
				.withClassroom(q.getClassroomId())
				.withPostedByUser(q.getPostedBy())
				// TODO: 10/02/19 this is a bug
//				.withPublicOnly(true)
				.withAccountId(user.getAccount().getAccountId())
				.withApprovedOnly()
				.withOderBy("p", "postdate", QuestionQueryBuilder.ORDER.DESC)
				.withLimit(page, noOfResults);


		if(tagCriteriaExists(q)) {
			queryBuilder.withTagsCondition(q.getTagIds());
		}


     */
    public List<Integer> search(Question criteria){

        /*
        this method should be used only if there is some search criteria specified. Otherwise we can search directly
        from the DB
         */

        final Map<String, String> queryParamMap = new HashMap<String, String>();
        queryParamMap.put("q", "id:"+criteria.getQuestionId());
        queryParamMap.put("fl", "id");
        queryParamMap.put("sort", "id asc");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        final QueryResponse response;
        try {
            response = client.query(COLLECTION_NAME, queryParams);
            final SolrDocumentList documents = response.getResults();

            LOGGER.info("Found " + documents.getNumFound() + " documents");

            List<Integer> questionIds = new ArrayList<>();

            for(SolrDocument document : documents) {
                final String id = (String) document.getFirstValue("id");
                final String name = (String) document.getFirstValue("name");

                LOGGER.info("id: " + id + "; name: " + name);
                questionIds.add(Integer.valueOf(id));
            }
            return questionIds;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return Collections.emptyList();
    }
}
