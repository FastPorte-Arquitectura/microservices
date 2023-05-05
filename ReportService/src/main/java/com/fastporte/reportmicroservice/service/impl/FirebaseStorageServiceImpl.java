package com.fastporte.reportmicroservice.service.impl;

import com.fastporte.reportmicroservice.service.FirebaseStorageService;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.System;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    @Override
    public String uploadFile(String localPath, String customFileName) {

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
