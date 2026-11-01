package org.quan.portfolio.infomation;

import org.quan.portfolio.infomation.config.ProfileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ProfileProperties.class)
public class InfomationApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfomationApplication.class, args);
    }

}
