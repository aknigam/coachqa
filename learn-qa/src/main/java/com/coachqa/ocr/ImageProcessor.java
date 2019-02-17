package com.coachqa.ocr;

import com.coachqa.exception.ImageProcessingException;

public interface ImageProcessor {
    String extractText(byte[] data) throws ImageProcessingException;

}
