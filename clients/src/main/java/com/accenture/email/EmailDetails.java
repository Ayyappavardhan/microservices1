package com.accenture.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Created by Ayyappa on 2/3/2024
 *
 * @author : Ayyappa
 * @date : 2/3/2024
 * @project : microservices1
 */
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class EmailDetails {

    private String customerName;
    private String email;
}
