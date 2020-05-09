package com.coachqa.ws.controllor;

import com.coachqa.entity.AppUser;
import com.coachqa.entity.FileInfo;
import com.coachqa.repository.dao.FileUploadDao;
import com.coachqa.service.UserService;
import com.coachqa.ws.util.WSUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.UUID;

/**
 * Refer below links:
 * 1. http://www.baeldung.com/spring-mvc-image-media-data
 * 2. https://github.com/eugenp/tutorials/blob/master/spring-mvc-xml/src/main/java/com/baeldung/spring/controller/ImageController.java
 *
 */
@RestController
@RequestMapping("/api/image")
public class FileUploadResource {

    @Autowired
    @Qualifier("AWSFileUploadDao")
    private FileUploadDao fileUploadDao;

    @Autowired
    private UserService userService;

    // TODO: 22/04/19 change the API to accept String imageId
    @GetMapping(value="/{imageId}")
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable(value = "imageId") String imageId)  {
        ResponseEntity<byte[]> responseEntity;
        final HttpHeaders headers = new HttpHeaders();

        byte[] media = fileUploadDao.readImage(imageId);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public @ResponseBody
    FileInfo handleFileUpload(@RequestParam("file") MultipartFile file){

        AppUser user = WSUtil.getUser(userService);

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                return new FileInfo(fileUploadDao.persist(bytes, user.getAccount().getAccountId()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Unexpected error occurred while uploading file. Please try again", e);
            }
        } else {
            throw new RuntimeException("File not provided for upload");
        }
    }



}
