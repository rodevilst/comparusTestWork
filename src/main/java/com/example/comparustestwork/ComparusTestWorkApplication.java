package com.example.comparustestwork;

import com.example.comparustestwork.config.DataSourceProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = { org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration.class })
@EnableConfigurationProperties(DataSourceProperties.class)
public class ComparusTestWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComparusTestWorkApplication.class, args);
    }
}
