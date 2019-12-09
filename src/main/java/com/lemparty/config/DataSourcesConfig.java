package com.lemparty.config;

import com.lemparty.InventoryApplication;
import com.lemparty.SSLContextHelper;
import com.lemparty.dao.BatchDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

//turns out we don't need to specify the profile, or the properties; spring boot so smart
//@Profile("default")
//@PropertySource("application-dev.properties")
@Configuration
public class DataSourcesConfig {

    @Value( "${mongo.url}" )
    private String mongoURL;

    @Value( "${mongo.privatekey.name}" )
    private String mongoPrivateKeyName;

    @Bean
    public BatchDAO getBatchDAO() {
        if(mongoPrivateKeyName != null && !mongoPrivateKeyName.equals("")) {
            SSLContextHelper.setSslProperties(mongoPrivateKeyName);
        }
        return new BatchDAO(mongoURL);
    }




}
