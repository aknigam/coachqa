package com.coachqa.ocr.gcp;


import com.coachqa.exception.ImageProcessingException;
import com.coachqa.ocr.ImageProcessor;
import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Refer
 *
 * https://cloud.google.com/vision/docs/quickstart-client-libraries?authuser=1
 * https://cloud.google.com/vision/docs/detecting-text?authuser=1
 * https://cloud.google.com/vision/docs/ocr?authuser=1
 */
@Component
public class GCPCloudVisionImageProcessor implements ImageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(GCPCloudVisionImageProcessor.class);

    @Override
    public String extractText(byte[] data) throws ImageProcessingException {
        // TODO: 09/02/19 is this client reusable. Can I create once and use multiple times
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

            ByteString imgBytes = ByteString.copyFrom(data);

            // Builds the image annotation request
            List<AnnotateImageRequest> requests = new ArrayList<>();
            Image img = Image.newBuilder().setContent(imgBytes).build();
            Feature feat = Feature.newBuilder().setType(Type.DOCUMENT_TEXT_DETECTION).build();
            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            requests.add(request);

            // Performs label detection on the image file
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            String text = "";
            for (AnnotateImageResponse res : responses) {
                if (res.hasError()) {
                    throw new ImageProcessingException("Error: %s\n", res.getError().getMessage());
                }

                // For full list of available annotations, see http://g.co/cloud/vision/docs
                for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
                    text  = annotation.getDescription();
                    break;
                }
            }
            return text;
        } catch (IOException e) {
            throw new ImageProcessingException(e);
        }
    }
}