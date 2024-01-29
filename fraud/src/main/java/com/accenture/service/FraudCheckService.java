package com.accenture.service;

import com.accenture.entity.FraudCheckHistory;
import com.accenture.repository.FraudCheckHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.time.LocalDate;
import java.util.Hashtable;
import java.util.Optional;

@Service
@AllArgsConstructor
@Builder
@Slf4j
public class FraudCheckService {
    private final FraudCheckHistoryRepository fraudCheckHistoryRepository;

    public boolean isFraudulentCustomer(String customerEmail) {
        //email already exist or not, if exist return isFraudster boolean
        Optional<FraudCheckHistory> fraudulentDetailsOptional = fraudCheckHistoryRepository.findByEmail(customerEmail);
//        if (fraudulentDetails.getEmail().equals(customerEmail)) {
//            return fraudulentDetails.getIsFraudster();
//        }
        if (fraudulentDetailsOptional.isPresent()) {
            FraudCheckHistory fraudulentDetails = fraudulentDetailsOptional.get();
            if (fraudulentDetails.getEmail().equals(customerEmail)) {
                return fraudulentDetails.getIsFraudster();
            }
        }
        FraudCheckHistory checkHistory = FraudCheckHistory.builder()
                .email(customerEmail)
                .isFraudster(fraudCheckValidation(customerEmail))
                .createdDate(LocalDate.now())
                .build();
        fraudCheckHistoryRepository.save(checkHistory);
        return checkHistory.getIsFraudster();

    }

    private Boolean fraudCheckValidation(String customerEmail) {
        if (!hasValidMXRecord(customerEmail)) {
            return true;
        }

        if (customerEmail.contains("fraud")) {
            return true;
        }
        return false;
    }

    public boolean hasValidMXRecord(String email) {
        String domain = email.substring(email.indexOf("@") + 1);
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext ictx = new InitialDirContext(env);
            Attributes attrs = ictx.getAttributes(domain, new String[]{"MX"});
            Attribute attr = attrs.get("MX");
            return attr != null;
        } catch (Exception e) {
            log.error("MX validation throwing exception: {}",e.getMessage());
             throw new RuntimeException("");
        }
    }
}
