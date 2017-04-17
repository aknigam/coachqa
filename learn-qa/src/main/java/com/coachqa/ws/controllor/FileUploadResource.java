package com.coachqa.ws.controllor;

import com.coachqa.repository.dao.impl.FileUploadDao;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileUploadResource {

    private FileUploadDao fileUploadDao;

    @GetMapping
    public @ResponseBody
    String provideUploadInfo() {
        return "You can upload a file by posting to this same URL.";
    }

    @PostMapping
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
