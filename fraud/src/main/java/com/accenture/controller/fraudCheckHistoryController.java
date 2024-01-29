package com.accenture.controller;

import com.accenture.fraud.FraudCheckResponse;
import com.accenture.service.FraudCheckService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fraudster-check")
@Slf4j
public class fraudCheckHistoryController {

    private final FraudCheckService fraudCheckService;
    @GetMapping(path = "{email}")
    public FraudCheckResponse isFraudster(@PathVariable("email") String email){
        log.info("fraud request for email: {}", email);
        boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(email);
        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
