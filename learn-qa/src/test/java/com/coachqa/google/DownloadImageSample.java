package com.coachqa.google;

// Imports the Google Cloud client library
import com.google.cloud.ReadChannel;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * https://stackoverflow.com/questions/25141998/how-to-download-a-file-from-google-cloud-storage-with-java
 * https://cloud.google.com/storage/docs/downloading-objects#storage-download-object-java
 * --https://stackoverflow.com/questions/45037540/stream-file-from-google-cloud-storage
 *
 */
public class DownloadImageSample {

    public static void main(String[] args) throws Exception {
        downloadFile("images/anand/image3.jpg");
    }


    /*
    if the File name has a trailing slash then a folder will be created
     */
    public static void downloadFile(String fileName) throws Exception {
        // Instantiates a client
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Blob blob = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        blob = storage.get(BlobId.of("crajee-dev001", fileName));
        ReadChannel reader = blob.reader();

        // https://stackoverflow.com/questions/45037540/stream-file-from-google-cloud-storage
        WritableByteChannel outChannel = Channels.newChannel(outputStream);
        ByteBuffer bytes = ByteBuffer.allocate(Math.toIntExact(blob.getSize()));
        while (reader.read(bytes) > 0) {
            bytes.flip();
            outChannel.write(bytes);
            bytes.clear();
        }


        System.out.println(outputStream.toByteArray().length);



    }

    public static void downloadFile() throws Exception {
        // Instantiates a client
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Blob blob = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        blob = storage.get(BlobId.of(null, null, 1544795590789524l));
//        blob = storage.get(BlobId.of("crajee-dev001", "images/anand/image7.jpg", 1544795590789524l));
        ReadChannel reader = blob.reader();

        // https://stackoverflow.com/questions/45037540/stream-file-from-google-cloud-storage
        WritableByteChannel outChannel = Channels.newChannel(outputStream);
        ByteBuffer bytes = ByteBuffer.allocate(Math.toIntExact(blob.getSize()));
        while (reader.read(bytes) > 0) {
            bytes.flip();
            outChannel.write(bytes);
            bytes.clear();
        }


        System.out.println(outputStream.toByteArray().length);



    }

}