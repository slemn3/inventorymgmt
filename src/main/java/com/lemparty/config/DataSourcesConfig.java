package com.lemparty.config;

import com.lemparty.dao.BatchDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//turns out we don't need to specify the profile, or the properties; spring boot so smart
//@Profile("default")
//@PropertySource("application-dev.properties")
@Configuration
public class DataSourcesConfig {

    @Value( "${mongo.url}" )
    private String mongoURL;

    @Bean
    public BatchDAO getBatchDAO() {
        return new BatchDAO(mongoURL);
    }


}
