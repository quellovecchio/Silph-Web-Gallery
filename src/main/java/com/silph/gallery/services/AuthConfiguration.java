package com.silph.gallery.services;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AuthConfig
 */
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    private DataSource dataSource;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { 
            http
            .authorizeRequests()
            .antMatchers("/", "/gallery", "/album/**", "/photo/**", "/photographer/**", "/cart", "/addToCart/**", "/confirmCart").permitAll()
            .antMatchers("/employeeDashboard/**").hasAnyAuthority("EMPLOYEE")
            .and()
            .formLogin()
            .defaultSuccessUrl("/dashboard")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .and()
            .csrf()
            .disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
        auth.jdbcAuthentication()
            .dataSource(this.buildDatasource())
            .authoritiesByUsernameQuery(
            "SELECT email, role FROM employee WHERE email=?")
            .usersByUsernameQuery(
            "SELECT email, password, 1 as enabled FROM users WHERE email=?");
    }

    @Bean
    public DataSource buildDatasource() {  
        DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.driverClassName(
        environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.url(environment.getProperty("spring.datasource.url"));
        dataSource.username(environment.getProperty("spring.datasource.username"));
        dataSource.password(environment.getProperty("spring.datasource.password"));
        return dataSource.build();
    }
    
}