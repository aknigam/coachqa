package com.coachqa.ws.controllor;

import com.coachqa.repository.dao.impl.FileUploadDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private FileUploadDao fileUploadDao;


    @GetMapping(value="/{imageId}")
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable(value = "imageId") Integer imageId)  {
        ResponseEntity<byte[]> responseEntity;
        final HttpHeaders headers = new HttpHeaders();

        byte[] media = fileUploadDao.readImage(imageId);
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        String name = "tempFileName";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Integer fileId = fileUploadDao.persist(bytes);
                return fileId.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }


}
