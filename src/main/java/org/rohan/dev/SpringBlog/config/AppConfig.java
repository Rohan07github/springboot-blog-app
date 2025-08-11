package org.rohan.dev.SpringBlog.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class AppConfig {

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String mail_transport_protocol;

    @Value("${spring.mail.port}")
    private String spring_mail_port;

    @Value("${spring.mail.username}")
    private String spring_mail_username;

    @Value("${spring.mail.password}")
    private String spring_mail_password;

    @Value("${mail.smtp.auth}")
    private boolean mail_smtp_Auth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean mail_smtp_starttls_enable;

    @Value("${spring.mail.ssl.trust}")
    private String smtp_ssl_Trust;
    
    @Value("${spring.mail.host}")
    private String spring_mail_host;

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    
        mailSender.setHost(spring_mail_host);
        mailSender.setPort(Integer.parseInt(spring_mail_port));

        mailSender.setUsername(spring_mail_username);
        mailSender.setPassword(spring_mail_password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mail_transport_protocol);
        props.put("mail.smtp.auth", mail_smtp_Auth);
        props.put("mail.smtp.starttls.enable", mail_smtp_starttls_enable);
        props.put("mail.smtp.ssl.trust", smtp_ssl_Trust);
        props.put("mail.debug", "true");    

        return mailSender;


    }
}
