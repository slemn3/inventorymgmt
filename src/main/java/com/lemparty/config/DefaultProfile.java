package com.lemparty.config;

import com.lemparty.dao.BatchDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default")
public class DefaultProfile {

    @Bean
    public BatchDAO getBatchDAO() {
        return new BatchDAO("mongodb://localhost:27017");
    }


}
