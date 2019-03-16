package com.coachqa.ws.controllor;


import com.coachqa.ws.ImageData;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Refer: https://www.journaldev.com/615/java-resize-image
 */
public class ImageProcessor {


    private static final Logger LOGGER = LoggerFactory.getLogger(ImageProcessor.class);


    public static ImageData resizeToStandardSize(byte[] bytes) {
        try {
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(bytes));
            int h = img.getHeight();
            int w = img.getWidth();
//            if(h <= 1000 || w <= 1000) {
//                return new ImageData(bytes);
//            }
            double aspectRatio = (double) img.getWidth(null)/(double) img.getHeight(null);

            BufferedImage standardImage = resizeImage(img, 1000, (int) (1000 / aspectRatio));
            BufferedImage thumbnailImage = resizeImage(img, 100, (int) (100 / aspectRatio));

            ByteArrayOutputStream stdos = new ByteArrayOutputStream();
            ByteArrayOutputStream tnos = new ByteArrayOutputStream();
            ImageIO.write(standardImage, "jpg", stdos);
            ImageIO.write(thumbnailImage, "jpg", tnos);

            // writing the file for debugging purpose only. Should be commented in production
            ImageData imageData = new ImageData();
//            wrtiteToFile(img, "orig.jpg");
//            wrtiteToFile(standardImage, "sdt.jpg");
//            wrtiteToFile(thumbnailImage, "tn.jpg");


            imageData.standardImage =   stdos.toByteArray();
            imageData.thumbnailImage =   tnos.toByteArray();

            return imageData;
        } catch (IOException e) {
            LOGGER.error("Unexpected error occurred while trying to resize the image", e);
            return new ImageData(bytes);
        }

    }


    private static void wrtiteToFile(BufferedImage tempImage, String filename) throws IOException {
        File newFilePJPG = new File("/Users/a.nigam/Documents/workspace/coachqa/learn-qa/src/test/resources/image/resize" +
                "/"+filename);
        ImageIO.write(tempImage, "jpg", newFilePJPG);
    }


    /**
     * This function resize the image file and returns the BufferedImage object that can be saved to file system.
     */
    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    public static void sample() throws IOException {

        File folder = new File("/Users/a.nigam/Documents/workspace/coachqa/learn-qa/src/test/resources/image");
        File[] listOfFiles = folder.listFiles();
        System.out.println("Total No of Files:" + listOfFiles.length);
        Image img = null;
        BufferedImage tempPNG = null;
        BufferedImage tempJPG = null;
        File newFilePNG = null;
        File newFileJPG = null;
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                img = ImageIO.read(new File("/Users/a.nigam/Documents/workspace/coachqa/learn-qa/src/test/resources" +
                        "/image/" + listOfFiles[i].getName()));


                double aspectRatio = (double) img.getWidth(null)/(double) img.getHeight(null);
                tempPNG = resizeImage(img, 100, (int) (100/aspectRatio));
                tempJPG = resizeImage(img, 100, (int) (100/aspectRatio));
//
//                tempPNG = resizeImage(img, 80, 80);
//                tempJPG = resizeImage(img, 80, 80);
                newFilePNG = new File("/Users/a.nigam/Documents/workspace/coachqa/learn-qa/src/test/resources/image/resize/" + listOfFiles[i].getName() + "_New.png");
                newFileJPG = new File("/Users/a.nigam/Documents/workspace/coachqa/learn-qa/src/test/resources/image/resize/" + listOfFiles[i].getName() + "_New.jpg");
                ImageIO.write(tempPNG, "png", newFilePNG);
                ImageIO.write(tempJPG, "jpg", newFileJPG);
            }
        }
        System.out.println("DONE");
    }


    public static void main(String[] args) throws IOException {
//        FileInputStream file =  new FileInputStream(new File("/Users/a.nigam/Documents/workspace/coachqa/learn-qa/src" +
//                "/test/resources/image" +
//                "/phone_13mp4_3mb.jpg"));

        FileInputStream file =  new FileInputStream(new File("/Users/a.nigam/Desktop/handwriting.jpeg"));

        byte[] b = IOUtils.toByteArray(file);
        BufferedInputStream bis =  new BufferedInputStream(file);

        System.out.println(b.length);
        ImageData id = resizeToStandardSize(b);
        System.out.println(id.standardImage.length);
    }


}
