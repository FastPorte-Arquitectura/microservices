package com.fastporte.reportmicroservice;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        ClassLoader classLoader = ReportMicroserviceApplication.class.getClassLoader();

        File file = new File(Objects.requireNonNull(classLoader.getResource("firebase_secret.json")).getFile());

        FileInputStream serviceAccount =
                new FileInputStream(file.getAbsolutePath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("gener8-c323f.appspot.com")
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);


        return app;
    }
}
