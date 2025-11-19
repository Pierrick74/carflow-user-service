package com.PulseLabs.carflow_user_service.services;

import com.PulseLabs.carflow_user_service.Error.ServeurDriverLicenseNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.List;


@Service
public class LicenseService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private RestTemplate restTemplate;


    public boolean isDriveLicenseValide(String driveLicense) {
        try {
            ResponseEntity<Boolean> response = restTemplate.getForEntity("http://DRIVERLICENSESERVICE/licenses/"+ driveLicense, Boolean.class);
            return response.getBody();
        } catch (Exception e) {
            throw new ServeurDriverLicenseNotFound("Error checking license: " + e.getMessage());
        }
    }

    /*
    public boolean isDriveLicenseValide(String driveLicense) {
        try {
            Boolean response = webClientBuilder.build()
                    .get()
                    .uri("http://DRIVERLICENSESERVICE/licenses/"+ driveLicense)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block(Duration.ofSeconds(5));
            return response;
        } catch (Exception e) {
            throw new ServeurDriverLicenseNotFound("Error checking license: " + e.getMessage());
        }
    }
*/
}
