package com.coachqa.ws;

public class ImageData {
    public byte[] standardImage;
    public byte[] thumbnailImage;

    public ImageData(byte[] bytes) {
        standardImage = bytes;
    }

    public ImageData() {

    }
}