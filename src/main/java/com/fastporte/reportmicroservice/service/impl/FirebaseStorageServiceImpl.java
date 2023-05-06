package com.fastporte.reportmicroservice.service.impl;

import com.fastporte.reportmicroservice.ReportMicroserviceApplication;
import com.fastporte.reportmicroservice.service.FirebaseStorageService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.System;
import java.util.Objects;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {


    @Override
    public String uploadFile(String localPath, String customFileName) throws IOException {

        String firebaseSecretJson = "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"gener8-c323f\",\n" +
                "  \"private_key_id\": \"026cd3e9e172bea21bd64961d798674a90403fdf\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQD3wvFh5q53xnu0\\n/9yZTN8+iBmsk3yCOSkfcFhq2cqvWIBZ0m7E9dBmdyVVZ3u/IY5zn6tLUyogCx1P\\nT4rcindDk7eWOQv1v4uNIX0vZDYbkj0aqMy8tICTK/IHqJsJ2eQ0mCNCkVZHN4nG\\nWD6CF92Uvd1WWCRI0iItjiIAybs3b25IcpHG3GRuSg63dCY45d1gR35ztHEhVVas\\nx9tL+2yt56fEf5h/6ocVlb+6IYjTcfOQfg+o0MRFhCBR3GwcOcBa0z1+dgg0nLgB\\nfxwEA4am27kxFC4f/aHyhstB2NVuMNtjIYFlSp2MbdY+qGKp71JY1Kl0tL76v2Eg\\nXkNpj6M7AgMBAAECggEAaoLl3l6bNmafm+o63bSMbId58hTde+Yy5Y8Yk6bZWZRE\\naVjrwcSjOMmTxSldq4czhEVqEFHKVEgy63uYiMMGDokcbXpMx/L2fU+eT5dXI3c0\\nyZcznRRECgDkZaJ4ND31ux6EWgO9AZTAqAIbrTWxTlZpJstxndSoWMk3mPsgfu2S\\nA430PFTaJIaQf3FlwrRNUeqa34OkEnW2lAKqF+1YwbxMYGroGEjjw6FrRHiTRt9m\\ngV2ysb/Y0N2AGs1C7++0LJ3jKnxZ8YVJpgNibsddozxj+B7izOvajyBqHpDdiOa4\\n4ETlcAQqqu2qhk0yx0dNRYYrCgMKqrbnKsp8oZIPUQKBgQD8aBWg3eRaBcYWGd1y\\nV4L/x5ofj6/qzanUf8I9NmjvqUInNdy89tYPfZFvHQQFpAqvRMyWe9IFRgTxe4Ty\\nvDCeXsiTKp2igHX6U8QCsbnOMgc6r7UdQpUbyW3yUHPXeM2F6WNAGX6cvdbmZ1Te\\nZT7dSeUs5sUQfEMMo2YEnshKDwKBgQD7Se3VpfS6mVuHBHc8EPrqxQmlF1CU4d+6\\nRxtVQrNItQD2+5IBuU0zwAXHWw8DSkZX5atd4qCNKWsl0iGhasUca7Z9sRggFb8E\\nqvo1fN5ewAK2+1I1mATHQ8QPYTPjZF3roNW8qa5bbLnjW1FTfPzjEaFR/f0o3y/c\\nKz/12JVwFQKBgQC0J7JwTJBldMOLGNEHgNDeseAe8Nb2N+8k3j+Mo15I2T1/cSFp\\nuqDVE6IPdd1eDVUW/nssWZpw6yYyGiCthIcLMsnkyMEUsK4Ei29XIPVm6CxixfD2\\nvoRg/ee80ZGtVOsUzp1prgjB5XoSn+RijE9PcSPjZZ94gcj1p1b0q0R4HwKBgQCU\\nIHg6QKGKfWweottGZRwD2DJfRucRXDRtsgzCrZbRfSEcdh8TNQ/1OXQul9a5scbV\\neWUS6syJaajnmn6IkE+w+S2R2hrGjwsSzVbAwOK0J6tyAaPsQ7hNXfzss/fKSxey\\nFhToKX3GFslc5C+Eb2EC2RmnI3lf5nBn8Ha7jPhKIQKBgCmCo+22/h/Q6gn6gZIk\\nk5ICzRvrpm6RrL/M4DYtI2LcBKAJZ8iXRhIZFHo+n9GuWdS6KkNd9SZTRterFk2R\\nfg6PJb1x5NPt6r9bsWm6gd2zkfAcZ2VCFiZ1gQIlQEHVwVcPJJJq94vBGWZ6OxVt\\nOF1lNwjWZL59pdixwembLxEn\\n-----END PRIVATE KEY-----\\n\",\n" +
                "  \"client_email\": \"firebase-adminsdk-lf6f4@gener8-c323f.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"101596253103315618607\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-lf6f4%40gener8-c323f.iam.gserviceaccount.com\"\n" +
                "}";


        InputStream serviceAccount = new ByteArrayInputStream(
                firebaseSecretJson.getBytes(StandardCharsets.UTF_8));

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
