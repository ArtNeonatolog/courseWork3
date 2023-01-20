package com.me.artsafuanov.coursework3.configuration;
import com.me.artsafuanov.coursework3.components.SizeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SizeConverter());
    }
}
