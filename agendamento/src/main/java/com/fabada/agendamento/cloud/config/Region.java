package com.fabada.agendamento.cloud.config;

import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.providers.AwsRegionProvider;

@Configuration
public class Region implements AwsRegionProvider {
    @Override
    public software.amazon.awssdk.regions.Region getRegion() {
        return software.amazon.awssdk.regions.Region.US_EAST_1;
    }
}
