package com.coachqa;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Collections;

public class GoogleTokenVerifier {

    public static void main(String[] args) throws Exception {


        HttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList("CLIENT_ID"))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)

        GoogleIdToken idToken = verifier.verify("eyJhbGciOiJSUzI1NiIsImtpZCI6IjA4ZDMyNDVjNjJmODZiNjM2MmFmY2JiZmZlMWQwNjk4MjZkZDFkYzEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIxNTMyNDg3NTg5ODAtdXU5N3Q4bzVmM3FnNzRoNWw3c3JoZ3UxNjNmZ2RrdGQuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIxNTMyNDg3NTg5ODAtdmk4cjFra3NnajU5cmZzcWhxMGw2YW1lNm5nNjQ5MDYuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDgyMTA4OTgwMDU1NTQ5MzQ1ODUiLCJlbWFpbCI6ImFuYW5kLmsubmlnYW1AZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJBbmFuZCBOaWdhbSIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vLXBpSXRTNlpEckQ4L0FBQUFBQUFBQUFJL0FBQUFBQUFBaHpJL1B6aDg4TGR1NXF3L3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJBbmFuZCIsImZhbWlseV9uYW1lIjoiTmlnYW0iLCJsb2NhbGUiOiJlbiIsImlhdCI6MTU0NzYxNjQ1MiwiZXhwIjoxNTQ3NjIwMDUyfQ.Up2vAEVFe82enviDKAJnxGULR2ESom7wKMQe0gyOp1YBauk2-Bn4g-NshYZbiuqIWqVT_w5aQRjKYDWpOX8jYcj6L1lsUV72gf_HQL6L0ecLOLWRE-Frj9x9rSDARZ6oV012sVFpJ-SySENPNPbXpx3RKIHQldRMg26BoSdTPAaJ431VgRW2fv07aPoyb0ZnYw2ZHFwubHoryJeVjK6lW0kljDSpf2yVLfx-BMalTCGnJZRXFGZ2TAq8EYqpLimy9B33htHkb94wipKHis18xRymKHNAIrlL0G9S1zWRbaZqErBU_OfvD0-du2Y66p4nflT7_74HyBrdLVbqyMVFuQ");
        if (idToken != null) {
            Payload payload = idToken.getPayload();


            // Print user identifier
            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            // Get profile information from payload
            String email = payload.getEmail();
            boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String locale = (String) payload.get("locale");
            String familyName = (String) payload.get("family_name");
            String givenName = (String) payload.get("given_name");

            // Use or store profile information
            // ...

        } else {
            System.out.println("Invalid ID token.");
        }
    }


}

