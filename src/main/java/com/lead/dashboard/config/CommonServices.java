package com.lead.dashboard.config;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommonServices {
    public String getUuid() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        return uuidAsString.replace("-","");
    }

}
