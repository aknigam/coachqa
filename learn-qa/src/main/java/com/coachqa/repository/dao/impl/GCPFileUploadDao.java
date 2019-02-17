package com.coachqa.repository.dao.impl;

import com.coachqa.repository.dao.FileUploadDao;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository("GCPFileUploadDao")
public class GCPFileUploadDao implements FileUploadDao {

    private static Logger LOGGER = LoggerFactory.getLogger(GCPFileUploadDao.class);

    private static final String GCP_STORAGE_MEDIAL_LINK = "media_link";
    private static final String GCP_STORAGE_SELF_LINK = "self_link";
    private static final String GCP_STORAGE_FILE_NAME = "file_name";
    private static final String GCP_STORAGE_BUCKET = "bucket";
    private static final String GCP_STORAGE_GENERATION = "generation";
    private static final String GCP_STORAGE_ETAG = "etag";
    private static final String GCP_STORAGE_CRC32 = "crc32";


    public static final String BUCKET_CRAJEE_DEV = "crajee-dev001";

    public static final String GCP_STORAGE_PREFIX = "g-";

    private Storage storage;

    @PostConstruct
    public void init(){
        storage = StorageOptions.getDefaultInstance().getService();
    }

    /**
     *
     * Refer: https://cloud.google.com/storage/docs/reference/libraries#client-libraries-install-java
     *
     *
     * For this to work we must set the system property whose value is the file google-serviceaccounttemp_credentials
     * .json
     *
     * GOOGLE_APPLICATION_CREDENTIALS=/path/google-serviceaccounttemp_credentials.json
     * @param bytes
     * @return
     */
    public String persist(byte[] bytes) {

        InputStream fis = null;
        try {
            fis = new ByteArrayInputStream(bytes);

            BlobInfo blobInfo =
                    storage.create(
                            BlobInfo
                                    .newBuilder(BUCKET_CRAJEE_DEV, UUID.randomUUID().toString())
                                    // Modify access list to allow all users with link to read file
                                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                                    .build(),
                            fis);


            Map<String, String> metadata = new HashMap<>();
            metadata.put( GCP_STORAGE_MEDIAL_LINK ,  blobInfo.getMediaLink());
            metadata.put( GCP_STORAGE_SELF_LINK ,  blobInfo.getSelfLink());
            metadata.put( GCP_STORAGE_FILE_NAME ,  blobInfo.getBlobId().getName());
            metadata.put( GCP_STORAGE_BUCKET ,  blobInfo.getBlobId().getBucket());
            metadata.put( GCP_STORAGE_GENERATION , Long.toString(blobInfo.getBlobId().getGeneration()));
            metadata.put( GCP_STORAGE_ETAG ,  blobInfo.getEtag());
            metadata.put( GCP_STORAGE_CRC32 ,  blobInfo.getCrc32c());

            String imageId = GCP_STORAGE_PREFIX + blobInfo.getBlobId().getName();

            LOGGER.info("Uploaded image id {}", imageId);


            return imageId;
        }
        finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException("Unexpected error occurred while uploading file. Please try again");
                }
            }
        }

    }



    public byte[] readImage(Integer imageId) {


        return null;

    }

}
