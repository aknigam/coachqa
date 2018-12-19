package com.coachqa.entity;

import java.util.Map;

public class ImageInfo {

    private String id;

    private Map<String, String> metadata;

    public ImageInfo(String imageId) {
        this.id = imageId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
