package com.accenture.fraud;

import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("fraud")
public interface FraudClient {

    @GetMapping(path = "api/v1/fraudster-check/{email}")
    FraudCheckResponse isFraudster(@PathVariable("email") String email);
//    default String getFraudsterCheckUrl(String email) throws NoSuchMethodException {
//        GetMapping getMapping = FraudClient.class.getDeclaredMethod("isFraudster", String.class)
//                .getAnnotation(GetMapping.class);
//        String[] paths = getMapping.value();
//        String path = paths.length > 0 ? paths[0] : "";
//        return path.replace("{email}", email);
//    }
}
