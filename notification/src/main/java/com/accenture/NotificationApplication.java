package com.accenture;

//import com.accenture.config.NotificationConfig;
//import com.accenture.config.RabbitMqMessageProducer;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = {"com.accenture",
        "com.accenture.config"})
@EnableDiscoveryClient
@EnableFeignClients(
        basePackages = {"com.accenture.email"})
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);

    }
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMqMessageProducer producer, NotificationConfig notificationConfig){
//        return args -> {
//            producer.publish(new Person("Ayyappa", 24),notificationConfig.getInternalExchange(),notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
//    record Person(String name, int age){};
}
