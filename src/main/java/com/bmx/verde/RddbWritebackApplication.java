package com.bmx.verde;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
@EnableSqs
@EnableTransactionManagement
public class RddbWritebackApplication {

    public static void main(String[] args) {
        SpringApplication.run(RddbWritebackApplication.class, args);
    }
}
