package com.accenture.rabbitMq;

import com.accenture.email.EmailDetails;
import com.accenture.service.EmailSendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by Ayyappa on 2/6/2024
 *
 * @author : Ayyappa
 * @date : 2/6/2024
 * @project : microservices1
 */
@Component
@AllArgsConstructor
@Slf4j
public class EmailConsumer {
    private final EmailSendService emailSendService;
    @RabbitListener(queues = "${rabbitmq.queue.emailsender}")
    public void consumer(EmailDetails emailDetails){
        log.info("consumed {} from queue",emailDetails);
        emailSendService.send(emailDetails);
    }
}
