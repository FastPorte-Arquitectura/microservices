package com.fastporte.reportmicroservice.service.impl;

import com.fastporte.reportmicroservice.ReportMicroserviceApplication;
import com.fastporte.reportmicroservice.service.FirebaseStorageService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.System;
import java.util.Objects;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {


    @Override
    public String uploadFile(String localPath, String customFileName) throws IOException {

        ClassLoader classLoader = ReportMicroserviceApplication.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("firebase_secret.json")).getFile());
        FileInputStream serviceAccount =
                new FileInputStream(file.getAbsolutePath());

        StorageOptions options = StorageOptions.newBuilder()
                .setProjectId("gener8-c323f")
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        Storage storage = options.getService();


        try {
            var FIREBASE_PATH = "https://firebasestorage.googleapis.com/v0/b/";
            Path pathLocalUpload = Paths.get(localPath);
            BlobId blobId = BlobId.of("gener8-c323f.appspot.com", customFileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            Blob blob = storage.create(blobInfo, Files.readAllBytes(pathLocalUpload));
            return FIREBASE_PATH + blob.getBucket() + "/o/" + blob.getName() + "?alt=media";
        } catch (IOException e) {
            throw new RuntimeException("Ocurrio un error al subir el archivo");
        }
    }
}
