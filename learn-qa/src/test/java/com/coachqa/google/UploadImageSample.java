package com.coachqa.google;

// Imports the Google Cloud client library
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class UploadImageSample {

    private static final String GCP_STORAGE_MEDIAL_LINK = "media_link";
    private static final String GCP_STORAGE_SELF_LINK = "self_link";
    private static final String GCP_STORAGE_FILE_NAME = "file_name";
    private static final String GCP_STORAGE_BUCKET = "bucket";
    private static final String GCP_STORAGE_GENERATION = "generation";
    private static final String GCP_STORAGE_ETAG = "etag";
    private static final String GCP_STORAGE_CRC32 = "crc32";

    public static void main(String[] args) throws Exception {
        uploadImageInsideFolder();
//        createEmptyFolder("emptyfolder/");
//        createEmptyFolder("emptyfolder/folder1");
//        createEmptyFolder("emptyfolder/folder1/folder2/");
    }


    /*
    if the File name has a trailing slash then a folder will be created
     */
    public static void createEmptyFolder(String folderName) throws Exception {
        // Instantiates a client
        Storage storage = StorageOptions.getDefaultInstance().getService();


            BlobInfo blobInfo =
                    storage.create(
                            BlobInfo
                                    .newBuilder("crajee-dev001", folderName)
                                    // Modify access list to allow all users with link to read file
                                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                                    .build());

            System.out.println(blobInfo.getMediaLink());

    }

    /*
    if the file name is in path format with slashes then the file is created within the folder
    https://www.googleapis.com/storage/v1/b/crajee-dev001/o/images%2Fanand%2Fimage3.jpg


    {
 "kind": "storage#object",
 "id": "crajee-dev001/images/anand/image3.jpg/1544790846446985",
 "selfLink": "https://www.googleapis.com/storage/v1/b/crajee-dev001/o/images%2Fanand%2Fimage3.jpg",
 "name": "images/anand/image3.jpg",
 "bucket": "crajee-dev001",
 "generation": "1544790846446985",
 "metageneration": "1",
 "timeCreated": "2018-12-14T12:34:06.446Z",
 "updated": "2018-12-14T12:34:06.446Z",
 "storageClass": "REGIONAL",
 "timeStorageClassUpdated": "2018-12-14T12:34:06.446Z",
 "size": "303229",
 "md5Hash": "kPkGdHDdnVmVILTJqurDBA==",
 "mediaLink": "https://www.googleapis.com/download/storage/v1/b/crajee-dev001/o/images%2Fanand%2Fimage3.jpg?generation=1544790846446985&alt=media",
 "crc32c": "B8Vb5A==",
 "etag": "CIm7nq2qn98CEAE="
}

     */
    public static void uploadImageInsideFolder() throws Exception {
        // Instantiates a client
        Storage storage = StorageOptions.getDefaultInstance().getService();


        InputStream fis = null;
        try {
            fis = new BufferedInputStream(new FileInputStream("/Users/a" +
                    ".nigam/Documents/workspace/coachqa/learn-qa/src/test/resources/gcp/imagetotext1.jpg"));

            BlobInfo blobInfo =
                    storage.create(
                            BlobInfo
                                    .newBuilder("crajee-dev001", "images/anand/image10_readertoallusers.jpg")
                                    // Modify access list to allow all users with link to read file
                                    .setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role
                                            .READER))))
                                    .build(),
                            fis);

            Map<String, String> metadata = new HashMap<>();
            metadata.put( GCP_STORAGE_MEDIAL_LINK ,  blobInfo.getMediaLink());
            metadata.put( GCP_STORAGE_SELF_LINK ,  blobInfo.getSelfLink());
            metadata.put( GCP_STORAGE_FILE_NAME ,  blobInfo.getBlobId().getName());
            metadata.put( GCP_STORAGE_BUCKET ,  blobInfo.getBlobId().getBucket());
            metadata.put( GCP_STORAGE_GENERATION ,  blobInfo.getGeneratedId());
            metadata.put( GCP_STORAGE_ETAG ,  blobInfo.getEtag());
            metadata.put( GCP_STORAGE_CRC32 ,  blobInfo.getCrc32c());


            System.out.println(blobInfo.getMetadata());
            System.out.println(blobInfo.getMediaLink());
        }
        finally {
            if(fis != null)
                fis.close();
        }



    }
}