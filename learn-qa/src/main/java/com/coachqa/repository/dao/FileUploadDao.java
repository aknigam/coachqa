package com.coachqa.repository.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadDao  {

    final String GCP_STORAGE_PREFIX = "g-";
    final String AWS_STORAGE_PREFIX = "a-";

    public String persist(byte[] bytes, int accountId);

    public byte[] readImage(String imageId);

}
