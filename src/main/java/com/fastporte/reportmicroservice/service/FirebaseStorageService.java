package com.fastporte.reportmicroservice.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FirebaseStorageService {
    String uploadFile(String localPath, String customFileName) throws IOException;

}
