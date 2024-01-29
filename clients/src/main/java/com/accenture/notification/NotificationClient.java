package com.accenture.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("notification")
public interface NotificationClient {
    @PostMapping("api/v1/notifications")
    ResponseEntity<String> sendNotification(CustomerRegistrationRequest customerRegistrationRequest);
    default String[] getNotificationUrl() throws NoSuchMethodException {
        PostMapping postMapping = NotificationClient.class.getDeclaredMethod("sendNotification", CustomerRegistrationRequest.class)
                .getAnnotation(PostMapping.class);
        return postMapping.value();
    }

}
