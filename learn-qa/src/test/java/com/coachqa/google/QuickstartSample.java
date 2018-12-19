package com.coachqa.google;

// Imports the Google Cloud client library
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;


/**
 * https://cloud.google.com/java/getting-started/using-cloud-storage
 *
 * https://github.com/GoogleCloudPlatform/java-docs-samples/blob/master/storage/cloud-client/src/main/java/com/example/storage/QuickstartSample.java
 *
 *
 */
public class QuickstartSample {
    public static void main(String... args) throws Exception {

        // this system property should be set
//        System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", "/Users/a" +
//                ".nigam/Documents/workspace/coachqa/learn-qa/src/main/resources/learnqa.appconfig.properties");


        // Instantiates a client
        Storage storage = StorageOptions.getDefaultInstance().getService();

        Bucket bucket = storage.get("crajee-dev001");



        // The name for the new bucket
        String bucketName = "crajee-test001";  // "my-new-bucket";
        storage.delete(bucketName);
        // Creates the new bucket
//        Bucket bucket = storage.create(BucketInfo.of(bucketName));


        System.out.printf("Bucket %s created.%n", bucket.getName());




    }
}