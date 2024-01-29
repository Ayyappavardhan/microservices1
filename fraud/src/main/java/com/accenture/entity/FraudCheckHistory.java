package com.accenture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class FraudCheckHistory {

    @Id
    @SequenceGenerator(name = "fraudCheckHistory_sequence",
            sequenceName = "fraudCheckHistory_sequence",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "fraudCheckHistory_sequence")

    private Integer id;
    private String email;
    private Boolean isFraudster;
    private LocalDate createdDate;
}
