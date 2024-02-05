package com.accenture.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

/**
 * Created by Ayyappa on 2/3/2024
 *
 * @author : Ayyappa
 * @Date : 2/3/2024
 * @project : microservices1
 */
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class EmailSaveInDb {
    @Id
    @SequenceGenerator(name = "EmailSender_sequence",
            sequenceName = "EmailSender_sequence",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "EmailSender_sequence")
    private Integer id;
    private String customerName;
    private String email;
    private String emailBody;
}
