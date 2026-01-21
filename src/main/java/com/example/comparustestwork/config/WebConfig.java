package com.example.comparustestwork.config;

import com.example.comparustestwork.api.dto.SortField;
import com.example.comparustestwork.api.dto.SortOrder;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(String.class, SortOrder.class,
                source -> SortOrder.valueOf(source.toUpperCase()));

        registry.addConverter(String.class, SortField.class,
                source -> SortField.valueOf(source.toUpperCase()));
    }
}