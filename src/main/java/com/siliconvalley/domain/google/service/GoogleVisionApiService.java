package com.siliconvalley.domain.google.service;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class GoogleVisionApiService {

    @Value("${google.service.account.key}")
    private String serviceAccountKey;

    public Map<String, Float> detectLabels(String filePath) {
        List<AnnotateImageRequest> requests = new ArrayList<>();
        Map<String, Float> results = new HashMap<>();

        try {
            ImageSource imageSource = ImageSource.newBuilder().setImageUri(filePath).build();
            Image img = Image.newBuilder().setSource(imageSource).build();

            // LABEL_DETECTION 유형으로 변경
            Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();

            AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                    .addFeatures(feat)
                    .setImage(img)
                    .build();
            requests.add(request);

            GoogleCredentials credentials = GoogleCredentials.fromStream(
                    new ByteArrayInputStream(serviceAccountKey.getBytes(StandardCharsets.UTF_8))
            );
            ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
                    .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                    .build();

            try (ImageAnnotatorClient client = ImageAnnotatorClient.create(settings)) {
                BatchAnnotateImagesResponse response = client.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();

                for (AnnotateImageResponse res : responses) {
                    if (res.hasError()) {
                        results.put("Error", -1f);
                        log.info("Error occurred: " + res.getError().getMessage());
                        continue;
                    }

                    // EntityAnnotation 리스트로 변경
                    for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                        log.info("Detected label: " + annotation.getDescription() + " with confidence: " + annotation.getScore());
                        results.put(annotation.getDescription().toLowerCase(), annotation.getScore());
                    }
                }
            }
        } catch (IOException e) {
            results.put("Error", -1f);
            log.error("An IOException occurred: ", e);
        }

        return results;
    }
}
