package com.accenture.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record CustomerRegistrationRequest (String firstName,String lastName,String email){
}
