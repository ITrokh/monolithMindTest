package com.monolithmind.task.core.rest;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import com.monolithmind.task.core.model.dto.PurchaseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GoogleVerificationService {
    private static final String KEY_FOLDER_PATH = "/somePath";

    JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
    String applicationName = "testing app name";
    String packageName = "package.name.test";
    @Value("${spring.google.service-account-id}")
    private String serviceAccountId;
    private final Set<String> scopes = Collections.singleton(AndroidPublisherScopes.ANDROIDPUBLISHER);
    private static int stateNumber = 0;

    public ProductPurchase verify(PurchaseDto purchaseDto, boolean mock) {
        if (!mock) {
            try {
                HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
                GoogleCredential credential = new GoogleCredential.Builder()
                        .setTransport(httpTransport)
                        .setJsonFactory(jsonFactory)
                        .setServiceAccountId(serviceAccountId)
                        .setServiceAccountScopes(scopes)
                        .setServiceAccountPrivateKeyFromP12File(
                                new File(KEY_FOLDER_PATH)).build();

                AndroidPublisher pub = new AndroidPublisher.Builder
                        (httpTransport, jsonFactory, credential)
                        .setApplicationName(applicationName)
                        .build();
                final AndroidPublisher.Purchases.Products.Get get =
                        pub.purchases()
                                .products()
                                .get(packageName, purchaseDto.getProductId(), purchaseDto.getPurchaseToken());
                return get.execute();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            //mock returning of state. Return repeated changing state
            int state = stateNumber++ % 4;
            ProductPurchase productPurchase = new ProductPurchase();
            productPurchase.setPurchaseState(state);
            return productPurchase;
        }
    }
}
