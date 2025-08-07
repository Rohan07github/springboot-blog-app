package org.studyeasy.SpringBlog.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class AppConfig {
    
    @Bean
    public JavaMailSender getJavaMailSender(JavaMailSenderImpl mailSender) {
        // Spring Boot already configures most settings automatically
        // You can just add any additional properties here if needed
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.debug", "true");
        return mailSender;
    }
 }