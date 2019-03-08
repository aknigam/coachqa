package com.coachqa.service.listeners.question;

import com.coachqa.entity.Post;
import com.coachqa.enums.PostTypeEnum;
import com.coachqa.exception.QAEntityNotFoundException;
import com.coachqa.ocr.ImageProcessor;
import com.coachqa.service.IndexSearchService;
import com.coachqa.service.PostService;
import com.coachqa.service.listeners.ApplicationEventListener;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Possibly this can also be done by writing a Lamda function
 */
@Component
public class ImageToTextOcrListener implements ApplicationEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageToTextOcrListener.class);
    // TODO: 10/02/19 post service not getting injected.
    @Autowired
    private PostService postService;

    @Autowired
    private ImageProcessor imageProcessor;

    @Autowired()
    @Qualifier("solrindex")
    private IndexSearchService indexService;


    private static final String bucket ="crajee-dev001";


    public ImageToTextOcrListener(){
        System.out.println("ImageToTextOcrListener created");

    }

    @Override
    public void onEvent(ApplicationEvent event) {

        if(!isApprovedPostTypeEvent(event)) {
            return;
        }

        Integer postId = event.getEventSource();
        try {
            Post post = postService.getPostById(postId);
            if(post.getPostTypeEnum() == PostTypeEnum.ANSWER) {
                LOGGER.debug("Ingnoring answer for indexing as search is not supported on answers");
                return;
            }

            String content = post.getContent();

            List<String> imageUrls = getImagesFromPostContent(content);

            StringBuilder extractedText = new StringBuilder();
            for (String url: imageUrls) {
                try {
                    byte[] data = readImage(url);
                    extractedText.append(imageProcessor.extractText(data)).append("\t");
                } catch (Throwable t) {
                    LOGGER.error("Error reading image data for indexing",t);
                }
            }

            LOGGER.info("Extracted text from images of post {}", postId);
            LOGGER.info(extractedText.toString());

            indexPostExtractedtext(postId, extractedText.toString());

        } catch (QAEntityNotFoundException e) {
            LOGGER.error("Post with id {} does not exist", postId, e);
        } catch (Throwable t) {
            LOGGER.error("Unexpected error occurred while extracting text from image for post Id", postId, t);
        }

    }

    private void indexPostExtractedtext(Integer postId, String imageExtractedText) {
        indexService.updatePostExtractedtext(postId, imageExtractedText);

    }

    private enum ImageStorageType {
        GCP,
        AWS,
        SERVER,
        PUBLIC
    }


    private Storage storage;

    @PostConstruct
    public void init(){
        storage = StorageOptions.getDefaultInstance().getService();
    }

    private byte[] readImage(String url) {
        ImageStorageType storageType = ImageStorageType.GCP;

        if(storageType == ImageStorageType.GCP) {
            url = url.substring(url.indexOf("g-")+2, url.length());;
            return readFromGCPStorage(url);
        }
        if(storageType == ImageStorageType.SERVER) {
//            fetchImageFromServer(url);
        }
        if (storageType == ImageStorageType.PUBLIC) {

        }
        if (storageType == ImageStorageType.AWS) {

        }
        return new byte[0];
    }


    private byte[] readFromGCPStorage(String url) {
        return storage.readAllBytes(bucket, url);
    }

    private List getImagesFromPostContent(String content) {
        List<String> urls = new ArrayList<>();
        int index;
        while ((index = content.indexOf("{{")) != -1) {
            String link = content.substring(index + 2, content.indexOf("}}"));
            urls.add(link);
            content = content.substring(content.indexOf("}}") + 2, content.length());
        }
        
        return urls;
    }


    private boolean isApprovedPostTypeEvent(ApplicationEvent event) {
        EventType eventType = event.getEventType();

        return (event.getStage() == EventStage.STAGE_TWO) &&
                ( eventType == EventType.QUESTION_POSTED
                || eventType == EventType.QUESTION_ANSWERED
                || eventType ==  EventType.POST_UPDATED
                        || eventType ==  EventType.POST_APPROVED);
    }

    public static void main(String[] args) {

        ImageToTextOcrListener lis = new ImageToTextOcrListener();
        lis.storage =StorageOptions.getDefaultInstance().getService();
        byte[] data = lis.readFromGCPStorage("071705ea-8746-4785-b7bd-494ae5940104");
        System.out.println(data.length);
    }



}
