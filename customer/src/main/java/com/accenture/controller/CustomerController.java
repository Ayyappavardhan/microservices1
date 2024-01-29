package com.accenture.controller;

import com.accenture.notification.CustomerRegistrationRequest;
import com.accenture.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/registration")
    public ResponseEntity<String> customerRegistration( @RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        try {
            log.info("Customer service started with customer :{}",customerRegistrationRequest);
            customerService.customerRegistration(customerRegistrationRequest);
            return ResponseEntity.ok("Customer registration successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register customer");
        }
    }

}
