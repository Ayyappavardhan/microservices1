package com.accenture.config;


import lombok.Getter;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Getter
@Configuration
public class EmailsenderQueueConfig {
    @Value("${rabbitmq.exchanges.internal}")
    private String internalExchange;
    @Value("${rabbitmq.queue.emailsender}")
    private String emailSenderQueue;
    @Value("${rabbitmq.routing-keys.internal-emailsender}")
    private String internalEmailSenderRoutingKey;

    @Bean
    public TopicExchange internalTopicExchange() {
        return new TopicExchange(this.internalExchange);
    }
    @Bean
    public Queue emailSenderQueue() {
        return new Queue(this.emailSenderQueue);
    }
    @Bean
    public Binding internalEmailSenderRoutingKey(){
        return BindingBuilder
                .bind(emailSenderQueue())
                .to(internalTopicExchange())
                .with(internalEmailSenderRoutingKey);
    }


}
