package com.accenture.entity;

import jakarta.persistence.*;
import jakarta.xml.bind.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.StringWriter;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_sequence",
            sequenceName = "notification_sequence",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "notification_sequence")
    private Integer id;
    private String customerXml;
    private String email;
    private String message;
    private LocalDate createdDate;


}
