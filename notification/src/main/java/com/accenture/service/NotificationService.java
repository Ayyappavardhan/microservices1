package com.accenture.service;

import com.accenture.entity.CustomerXml;
import com.accenture.entity.Notification;
import com.accenture.notification.CustomerRegistrationRequest;
import com.accenture.repository.NotificationRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.io.StringWriter;
import java.time.LocalDate;

@Service
@AllArgsConstructor
@Builder
public class NotificationService {
    private final NotificationRepository notificationRepository;
    public void sendNotification(CustomerRegistrationRequest customerResponse) {
        Notification notification= Notification.builder()
                .customerXml(convertCustomerToXml(customerResponse))
                .message(setUpMessage(customerResponse))
                .email(customerResponse.email())
                .createdDate(LocalDate.now())
                .build();
        notificationRepository.save(notification);
    }
    private String convertCustomerToXml(CustomerRegistrationRequest customer) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CustomerXml.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            CustomerXml customerXml = new CustomerXml();
            // Set the properties of the CustomerXml object based on the customerRegistrationRequest
            customerXml.setFirstName(customer.firstName());
            customerXml.setLastName(customer.lastName());
            customerXml.setEmail(customer.email());

            StringWriter writer = new StringWriter();
            marshaller.marshal(customerXml, writer);

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
    private String setUpMessage(CustomerRegistrationRequest response) {
        return String.format("""
            Dear %s,
                Customer with email: %s is successfully created.
            Thanks,
            Vardhan.
            """, response.firstName(), response.email());
    }
}
