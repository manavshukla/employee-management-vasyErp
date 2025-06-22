package com.employeemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cros -> cros.disable()).csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/employee/login", "/process-login", "/WEB-INF/jsp/**").permitAll()
                        .requestMatchers("/api/employee/add", "/api/employee/create", "/api/employee/update/**", "/api/employee/delete/**").hasRole("ADMIN")
                        .requestMatchers("/api/employee/home", "/api/employee/view", "/api/employee/view/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/api/employee/login")
                        .loginProcessingUrl("/process-login")
                        .defaultSuccessUrl("/api/employee/home", true)
                        .failureUrl("/api/employee/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/api/employee/login?logout=true")
                        .permitAll());

        return http.build();
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails userAdmin = User.withDefaultPasswordEncoder().username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();

        UserDetails userEmp = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userAdmin, userEmp);
    }
}
