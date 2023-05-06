package com.fastporte.reportmicroservice;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Configuration
public class FirebaseConfig {
    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        ClassLoader classLoader = ReportMicroserviceApplication.class.getClassLoader();

        String firebaseSecretJson = "{\n" +
                "  \"type\": \"service_account\",\n" +
                "  \"project_id\": \"gener8-c323f\",\n" +
                "  \"private_key_id\": \"9235362ad2af9cf89d6caa73e2df1f6ab0b3edda\",\n" +
                "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDTwhmvvJ+m+oqx\naYzU4bX0pBl0lPkDDaW1r1QXKjdsBgsNjN69TRJq6WBVbhC/MYrkCazMuQyQoctX\npGcKN1It+CLhQLm8qe6bMM3QSyLHWSl0hAvf34gs1qAv3ukom7nIu8UO0IG94fXg\nDgXXvGfQu/OEci1FVnoGsh452cSha81QJqg/Kn6W/nQlO09RXpR4Q41Nf0Qi2CQm\nhrrNN39KmhPZT2EyZZAos+CkvDjYF5YClRvz6UNZXJoFrqCkceBA9gH/gG1fizyX\nPYdmnR3lTkDDWVbhdva6Afa8CVYh/gA7rHyX6HTjJdRhSO7IH5Kt/7m9gz0cJmxA\nuXue545tAgMBAAECggEAPXWPWPcI60C6MkWtLzgXRFrAXJrELwOt/a5WXKbPERYr\n7RHer9iHqOn1ILZiO+uYIRlBwuVOZUfgKZ64nTlzK45iKThM8Bnt+7LNeU/lVzBk\nnMGaZK6O6lycf2hWCYgxArakuekATukfIJlRTs0K4IU8Z0twIji4D+twZF/09tg4\nr44BiCmQmbeuSiGmgFaff9xIXsZKKA7R0KvWu/hF8tCy8VrymtigYUgpmbKQ6ivH\nwjSJnyG9B6UO6v7VdWvyzot1MublHrWenepbY3tFKgoTrYF8MN6kyrjPLOd/DORH\nYbBhdaWLgib54YBjXM8upt0xtRN89Bg+FjLAzxitzQKBgQDxgWYdK/E9oD+xqHjK\ncHwoHtn51qojoPiMZ9/NDue9GbPBl8JEFl3lkOwiyJ+52166EVVI7CMEoQlpTgP8\nz6DLwWlT+ujwf7lqTWWJNMxmNHS5mq8vrj5VRW11Y14DF/o1T4VWf1KyI7UAr4c1\nead65uH/plqMdGeEt8aAH9VNpwKBgQDgd6ae1JJTzWjMV3rQwmYKA0CGCBI0OER7\nUCjvcRkoQu0ACRRcn9en4B0xdZtWSpJr9x0FrikqQenF+ZPjcpoxJTlRwMISN/OO\n639xf5W7e2bLoSHcH0AlL28hq+qYR4tHYGLq2ZkEA37S2n4we0sGzjznPbqw2Wtn\nTpSYftKNywKBgQCqo4VlI0FHC801WBZQ4322U2Yss1Jv7jLv8g0dtGRlJp3uMa5f\nsXYS2H0gbqT9C1U5HzolVkO/55DgyPDkVkgE79neLrEcDQMTazBM6/NoW7QF2Jfj\nlrgQRAf9I4KvqOdvd4p8/WWO3M/tdhiQ3i5KFoykuAPOrgjeq4Mj0FpEjQKBgQCT\nfiuO3BDWXfeJpcW+nK4d0Cs6pt0HUR22hWKQixlDuk4uositYB59YabtE+EhH1j8\n9mAsllbYPH1Wp/sEDg7BUjtKu8UJ4Zf5hka0lmgVzC0silFMaYBlrrhwQJ2iuAgN\nu4jj1l1UJNPVytn2E12mOCgOriIZCJQpSpobZeIGZwKBgDFq+jGoTw3xAvJE84/A\n/rLU73aJo4J5DOjFFdhafQDXltEIOsQuMGOHhOK8dGOk2dfGS0155Ky0nJpBnyvI\ngzR6MPeTVdJkRUJ27RN2ShA943bRLM8Pk1YHciyPZyXQoFEjw/eaMHGxaufzJaV/\nL526FGOcDdQACy7tNoO2BF36\n-----END PRIVATE KEY-----\n\",\n" +
                "  \"client_email\": \"firebase-adminsdk-lf6f4@gener8-c323f.iam.gserviceaccount.com\",\n" +
                "  \"client_id\": \"101596253103315618607\",\n" +
                "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-lf6f4%40gener8-c323f.iam.gserviceaccount.com\"\n" +
                "}";


        InputStream serviceAccount = new ByteArrayInputStream(
                firebaseSecretJson.getBytes(StandardCharsets.UTF_8));

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket("gener8-c323f.appspot.com")
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);

        return app;
    }
}