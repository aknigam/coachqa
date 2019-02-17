package com.coachqa.service.listeners.question;

import com.coachqa.entity.Post;
import com.coachqa.exception.QAEntityNotFoundException;
import com.coachqa.ocr.ImageProcessor;
import com.coachqa.service.PostService;
import com.coachqa.service.listeners.ApplicationEventListener;
import notification.entity.ApplicationEvent;
import notification.entity.EventStage;
import notification.entity.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
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

    @Override
    public void onEvent(ApplicationEvent event) {

        if(!isApprovedPostTypeEvent(event)) {
            return;
        }

        Integer postId = event.getEventSource();
        try {
            Post post = postService.getPostById(postId);
            String content = post.getContent();

            List<String> imageUrls = getImagesFromPostContent(content);

            StringBuilder extractedText = new StringBuilder();
            for (String url: imageUrls) {
                byte[] data = readImage(url);
                extractedText.append(imageProcessor.extractText(data)).append("\t");
            }

            LOGGER.info("Extracted text from images of post {}", postId);
            LOGGER.info(extractedText.toString());

            // TODO: 09/02/19 this extracted text should be indexed

        } catch (QAEntityNotFoundException e) {
            LOGGER.error("Post with id {} does not exist", postId, e);
        } catch (Throwable t) {
            LOGGER.error("Unexpected error occurred while extracting text from image for post Id", postId, t);
        }




    }

    private byte[] readImage(String url) {
        return new byte[0];
    }

    private List getImagesFromPostContent(String content) {
        return Collections.emptyList();
    }

    private boolean isApprovedPostTypeEvent(ApplicationEvent event) {
        EventType eventType = event.getEventType();

        return (event.getStage() == EventStage.STAGE_TWO) && ( eventType == EventType.QUESTION_POSTED
                || eventType == EventType.QUESTION_ANSWERED
                || eventType ==  EventType.POST_UPDATED);
    }


}
