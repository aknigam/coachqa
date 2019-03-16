package com.coachqa.repository.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadDao  {

    public String persist(byte[] bytes, int accountId);

    public byte[] readImage(Integer imageId);

}
