package com.coachqa.repository.dao.impl;

import com.coachqa.repository.dao.FileUploadDao;
import com.coachqa.ws.ImageData;
import com.coachqa.ws.controllor.ImageProcessor;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;
import software.amazon.awssdk.services.s3.model.S3Response;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Refer:
 * https://docs.aws.amazon.com/sdk-for-java/v2/developer-guide/setup-project-maven.html
 * https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/javav2/example_code/s3/src/main/java/com/example/s3/S3BucketOps.java
 *
 * https://github.com/awsdocs/aws-doc-sdk-examples/blob/master/javav2/example_code/s3/src/main/java/com/example/s3/S3ObjectOperations.java
 *
 */


@Repository("AWSFileUploadDao")
public class AWSFileUploadDao implements FileUploadDao {

    private static Logger LOGGER = LoggerFactory.getLogger(AWSFileUploadDao.class);

    private static final String GCP_STORAGE_MEDIAL_LINK = "media_link";
    private static final String GCP_STORAGE_SELF_LINK = "self_link";
    private static final String GCP_STORAGE_FILE_NAME = "file_name";
    private static final String GCP_STORAGE_BUCKET = "bucket";
    private static final String GCP_STORAGE_GENERATION = "generation";
    private static final String GCP_STORAGE_ETAG = "etag";
    private static final String GCP_STORAGE_CRC32 = "crc32";


    public static final String BUCKET_CRAJEE_DEV = "crajee-storage-dev";

    public static final String GCP_STORAGE_PREFIX = "a-";

    private Storage storage;

    private ImageProcessor imageProcessor = new ImageProcessor();
    private S3AsyncClient s3;

    @PostConstruct
    public void init(){
        Region region = Region.AP_SOUTH_1;
        s3 = S3AsyncClient.create();


    }

    public String persist(byte[] bytes, int accountId) {
        // TODO: 18/02/19 use the accountId to segregate this accounts files
        ImageData id = ImageProcessor.resizeToStandardSize(bytes);
        String imageName = AWS_STORAGE_PREFIX + UUID.randomUUID().toString();
        persistsToGCPAndGetImageId(id.thumbnailImage, imageName+"_t");
        return persistsToGCPAndGetImageId(id.standardImage, imageName);

    }

    // https://www.baeldung.com/java-completablefuture
    private String persistsToGCPAndGetImageId(byte[] bytes, String imageName) {
        String bucket = BUCKET_CRAJEE_DEV;
        InputStream fis = null;
        try {
            fis = new ByteArrayInputStream(bytes);

            PutObjectRequest req = PutObjectRequest.builder().bucket(bucket).key(imageName).build();
            AsyncRequestBody body = AsyncRequestBody.fromBytes(bytes);
            CompletableFuture<PutObjectResponse> putResponse = s3.putObject(req, body);
            putResponse.handle(AWSFileUploadDao::s3ResponseErrorHandler);
            PutObjectResponse resp = putResponse.join();
            if(resp == null ) {
                throw new RuntimeException("error storing object in s3 bucket");
            }
            String imageId =  imageName;
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

//    private static S3Response s3ResponseErrorHandler(S3Response getObjectResponse, Throwable throwable) {
//        if(throwable != null) {
//            throwable.printStackTrace();
//            return null;
//        }
//        return getObjectResponse;
//    }


    private static <U> U s3ResponseErrorHandler(U response, Throwable throwable) {
        if(throwable != null) {
            throwable.printStackTrace();
            return null;
        }
        return response;
    }

    public byte[] readImage(String imageId) {
        String bucket = BUCKET_CRAJEE_DEV;


        GetObjectRequest request= GetObjectRequest.builder().bucket(bucket).key(imageId)
                .build();
        AsyncResponseTransformer<GetObjectResponse, ResponseBytes<GetObjectResponse>> transformer= AsyncResponseTransformer
                .toBytes();
        CompletableFuture<ResponseBytes<GetObjectResponse>> val = s3.getObject(request, transformer);
//        val.handle((s, t) -> {return s;});

        val.handle(AWSFileUploadDao::s3ResponseErrorHandler);
        ResponseBytes<GetObjectResponse> resp = val.join();
        if(resp == null) {
            throw new RuntimeException("Error in reading from S3 bucket");
        }
        byte[] data = resp.asByteArray();
        return data;

    }


}
