package com.coachqa.ocr.gcp;



import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Refer
 *
 * https://cloud.google.com/vision/docs/quickstart-client-libraries?authuser=1
 * https://cloud.google.com/vision/docs/detecting-text?authuser=1
 * https://cloud.google.com/vision/docs/ocr?authuser=1
 */
public class GCPCloudVisionImageProcessorTest {


    @Test
//    @Ignore
    public void testExtractText() throws IOException {

        GCPCloudVisionImageProcessor processor = new GCPCloudVisionImageProcessor();
//        String fileName = "/Users/a.nigam/Desktop/handwriting.jpeg";
        String fileName ="/Users/a.nigam/Documents/workspace/coachqa/learn-qa/src/test/resources/image/ieirodov1.jpg";

        // Reads the image file into memory
        Path path = Paths.get(fileName);
        byte[] data = Files.readAllBytes(path);
        String text = processor.extractText(data);

        System.out.println(text);

        /*
        extracted text should be as follows

        AMENDMENT I
        Congress shall make no law respecting an establishment
        of religion, or prohibiting the free exercise thereof;
        Or abroging the freedom of speech, or of the press;
        Or the right of the people peaceably to assemble,
        and to petition the government for a redress of greuances.
        -1791
         */

    }

}