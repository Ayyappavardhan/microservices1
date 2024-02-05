package com.accenture.service;

import com.accenture.email.EmailDetails;
import com.accenture.entity.EmailSaveInDb;
import com.accenture.repository.EmailSendRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Created by Ayyappa on 2/3/2024
 *
 * @author : Ayyappa
 * @date : 2/3/2024
 * @project : microservices1
 */
@Service
@AllArgsConstructor
public class EmailSendService {

    private final EmailSendRepository emailSendRepository;
    private final JavaMailSender javaMailSender;
    public void send(EmailDetails emailDetails) {
        sendEmail(emailDetails.getEmail(), emailDetails.getCustomerName());
        EmailSaveInDb emailSaveInDb = EmailSaveInDb.builder()
                .customerName(emailDetails.getCustomerName())
                .email(emailDetails.getEmail())
                .emailBody("Dear " + emailDetails.getCustomerName() + ",\n\nWelcome to our platform!")
                .build();
        emailSendRepository.save(emailSaveInDb);

    }
    private void sendEmail(String email, String customerName) {
        // Create a SimpleMailMessage object
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ayyappavardhan006@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("Invitation to Join Our Coding Workshop");
        mailMessage.setText(String.format(
                """
                        Dear %s,
                                 
                                 I hope this email finds you well.
                                 
                                 I am writing to invite you to join our upcoming coding workshop on [date of workshop]! This workshop aims to provide participants with hands-on experience in Java.
                                 
                                 What you will gain:
                                 
                                 Learn the fundamentals of Java
                                 Gain practical experience through coding exercises and projects
                                 Interact with industry experts and fellow coding enthusiasts
                                 Network with like-minded individuals in the field
                                 Benefits:
                                 
                                 This workshop will benefit both beginners and experienced coders by providing valuable insights into the latest trends in the industry and enhancing your coding skills.
                                 
                                 Call to action:
                                 
                                 If you have any questions or require further information, please don't hesitate to reach out. We look forward to seeing you at the workshop!
                                 
                        Best regards,         
                        Vardhan.
                                 """,customerName, LocalDate.now().plusDays(10)));

                    // Send the email
                    javaMailSender.send(mailMessage);

                }
            }
