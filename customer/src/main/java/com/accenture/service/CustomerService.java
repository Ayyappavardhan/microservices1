package com.accenture.service;

import com.accenture.config.RabbitMqMessageProducer;
import com.accenture.entity.Customer;
import com.accenture.exception.EmailAlreadyTakenException;
import com.accenture.exception.FraudsterException;
import com.accenture.exception.InvalidEmailException;
import com.accenture.fraud.FraudCheckResponse;
import com.accenture.fraud.FraudClient;
import com.accenture.notification.CustomerRegistrationRequest;
import com.accenture.notification.NotificationClient;
import com.accenture.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Builder
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMqMessageProducer messageProducer;
    public void customerRegistration(CustomerRegistrationRequest customerRegistrationRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        //todo: email is valid
        if (!isValidEmail(customerRegistrationRequest.email())) {
            log.info("not valid email");
            throw new InvalidEmailException("Email is not valid");
        }

        //todo: email is taken
        boolean emailExist = customerRepository.existsByEmail(customerRegistrationRequest.email());
        if (emailExist) {
            log.info("email already taken");
            throw new EmailAlreadyTakenException("Email already taken");
        }
        // fraud check by using email
        fraudster(customerRegistrationRequest);
        customerRepository.save(customer);
        //todo: send notification
        notificationService(customerRegistrationRequest);
    }
    private boolean fraudster(CustomerRegistrationRequest customerRegistrationRequest) {
        FraudCheckResponse fraudCheckResponse;
        try {

            /*
            without open feign we are using resttemplate
             */
//             fraudCheckResponse= restTemplate.getForObject("http://FRAUD/api/v1/fraudster-check/{email}",
//                    FraudCheckResponse.class, customerRegistrationRequest.getEmail());
            log.info("calling fraud service");
            fraudCheckResponse = fraudClient.isFraudster(customerRegistrationRequest.email());

        } catch (Exception e) {
            // Handle the exception here
            log.error("Error occurred while calling the fraudster check endpoint: {}", e.getMessage());
            // Perform any necessary error handling or fallback logic
            throw new RuntimeException();
        }
        log.error("isFraudster :{}", fraudCheckResponse.isFraudster());
        if (fraudCheckResponse.isFraudster()) {
            throw new FraudsterException(" Email is Fraudster ");
        }
        return false;
    }
    private ResponseEntity<String> notificationService(CustomerRegistrationRequest customerRegistrationRequest) {
        ResponseEntity<String> stringResponseEntity = null;
        try {
            log.info("Calling the notification service with URL:{}", (Object) notificationClient.getNotificationUrl());
            stringResponseEntity = notificationClient.sendNotification(customerRegistrationRequest);
            log.info("Response from notification service: {}", stringResponseEntity);
        } catch (Exception e) {
            log.error("Error to send notification: {}", e.getMessage());
        }
        return stringResponseEntity;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        return EMAIL_PATTERN.matcher(email).matches();
    }

}
