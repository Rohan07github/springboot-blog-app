package org.studyeasy.SpringBlog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.studyeasy.SpringBlog.util.constants.Privillages;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    public static final String[] WHITELIST = {
            "/",
            "/home",
            "/login",
            "/register",
            "/posts/**",
            "/db-console/**",
            "/css/**",
            "/images/**",
            "/js/**",
            "/fonts/**",
            "/webjars/**"
    };

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Enable CSRF protection for web app unless explicitly disabling for APIs only
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/db-console/**") // example: disable CSRF for H2 console or APIs if you need
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin()) // Allow H2 console frames if used
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(WHITELIST).permitAll()
                .requestMatchers("/profile/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/editor/**").hasAnyRole("ADMIN", "EDITOR")
                .requestMatchers("/test").hasAuthority(Privillages.ACCESS_ADMIN_PANEL.getPrivillage())
                .anyRequest().authenticated() // Secure all other endpoints
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            )
            .rememberMe(rememberMe -> rememberMe
                .rememberMeParameter("remember-me")
                // Optionally configure token validity (e.g., valid for 14 days)
                .tokenValiditySeconds(14 * 24 * 60 * 60)
            )
            // Remove or enable HTTP Basic depending on need:
            //.httpBasic(Customizer.withDefaults())
            ;

        return http.build();
    }
}