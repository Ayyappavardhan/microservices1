package com.accenture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
        (scanBasePackages = {"com.accenture",
                "com.accenture.config"})
@EnableDiscoveryClient
public class SendingEmailApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SendingEmailApplication.class,args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMqMessageProducer producer, EmailsenderQueueConfig emailsenderQueueConfig){
//        return args -> {
//            producer.publish(new Person("Ayyappa", 24),emailsenderQueueConfig.getInternalExchange(),emailsenderQueueConfig.getInternalEmailSenderRoutingKey());
//        };
//    }
    //record Person(String name, int age){};
}
