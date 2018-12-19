package com.coachqa.repository.dao;

import com.coachqa.entity.ImageInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface FileUploadDao  {

    public ImageInfo persist(byte[] bytes);

    public byte[] readImage(Integer imageId) ;
}
