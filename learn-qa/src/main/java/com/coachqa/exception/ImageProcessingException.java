package com.coachqa.exception;

import java.io.IOException;

public class ImageProcessingException extends RuntimeException {
    public ImageProcessingException(String s, String message) {
        super(message);
    }

    public ImageProcessingException(IOException e) {
        super(e);
    }
}
