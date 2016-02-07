package com.coachqa.service;

import com.coachqa.repository.dao.impl.FileUploadDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadService {

    private FileUploadDao fileUploadDao;

    @RequestMapping(value="/upload", method=RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file){
        String name = "tempFileName";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                int fileId = fileUploadDao.persist(bytes);

                return "You successfully uploaded " + name + "!";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }


}
