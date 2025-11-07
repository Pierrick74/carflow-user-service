package com.PulseLabs.carflow_user_service.services;

import com.PulseLabs.carflow_user_service.Error.ServeurDriverLicenseNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class LicenseService {
    private final RestTemplate restTemplate;

    @Value("${license.service.url:http://192.168.1.87:8084}")
    private String licenseServiceUrl;

    public LicenseService() {
        this.restTemplate = new RestTemplate();
    }

    public boolean isDriveLicenseValide(String driveLicense) {
        try {
            String url = licenseServiceUrl + "/licenses/" + driveLicense;
            String response = restTemplate.getForObject(url, String.class);
            return "true".equals(response);
        } catch (Exception e) {
            throw new ServeurDriverLicenseNotFound("Error checking license: " + e.getMessage());
        }
    }

}
