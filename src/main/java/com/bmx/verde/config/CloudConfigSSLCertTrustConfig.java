package com.bmx.verde.config;

import java.io.File;
import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
@Profile("local")
/** This config is used to trust a self signed ssl cert */
public class CloudConfigSSLCertTrustConfig {
    private static final String TRUST_STORE_PASS = "ec2proxy";
    public static final String TRUST_STORE_LOCATION = "classpath:truststore/ec2ProxyAuth.jks";

    @Autowired
    ConfigClientProperties properties;

    @Bean
    public ConfigServicePropertySourceLocator configServicePropertySourceLocator() throws Exception {
        final char[] password = TRUST_STORE_PASS.toCharArray();

        File file = ResourceUtils.getFile(TRUST_STORE_LOCATION);

        SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(file, password, password)
                .loadTrustMaterial(file, password, new TrustSelfSignedStrategy()).build();
        CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslContext)
                .setSSLHostnameVerifier((s, sslSession) -> true).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        ConfigServicePropertySourceLocator configServicePropertySourceLocator = new ConfigServicePropertySourceLocator(
                properties);
        configServicePropertySourceLocator.setRestTemplate(new RestTemplate(requestFactory));
        return configServicePropertySourceLocator;
    }
}
