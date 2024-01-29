package com.accenture.controller;

import com.accenture.notification.CustomerRegistrationRequest;
import com.accenture.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notifications")
@AllArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;
    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
       log.info("Notification service is started with email:{}", customerRegistrationRequest.email());
        notificationService.sendNotification(customerRegistrationRequest);
        return ResponseEntity.ok().build();
    }
}
