package com.lead.dashboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Value("${aws_path}")
    private String s3BaseUrl;

    public String getS3BaseUrl() {
        return s3BaseUrl;
    }
}