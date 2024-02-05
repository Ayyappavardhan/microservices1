package com.accenture.controller;


import com.accenture.email.EmailDetails;
import com.accenture.service.EmailSendService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ayyappa on 2/3/2024
 *
 * @author : Ayyappa
 * @date : 2/3/2024
 * @project : microservices1
 */
@RestController
@RequestMapping("api/v1/sendEmail")
@AllArgsConstructor
public class EmailSenderController {

    private final EmailSendService emailSendService;
    @PostMapping
    public void sendEmail(@RequestBody EmailDetails emailDetails){
        emailSendService.send(emailDetails);
    }

}
