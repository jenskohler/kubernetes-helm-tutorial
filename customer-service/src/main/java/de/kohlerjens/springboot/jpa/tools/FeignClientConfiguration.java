package de.kohlerjens.springboot.jpa.tools;


import feign.httpclient.ApacheHttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import feign.Client;

import javax.net.ssl.SSLContext;


/**
 * Required configuration for mTLS communication with springboot-starter-openfeign
 */
@Configuration
public class FeignClientConfiguration {

    @Bean
    public Client feignClient() {
        return new ApacheHttpClient(getHttpClient());
    }

    private CloseableHttpClient getHttpClient() {
        int timeout = 10000;
        try {
            SSLContext sslContext = SSLContextBuilder.create()
                    .loadTrustMaterial(new TrustSelfSignedStrategy()).build();
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .build();
            return HttpClientBuilder
                    .create()
                    .useSystemProperties()
                    .setDefaultRequestConfig(config)
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }




}