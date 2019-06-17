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

/**
 * AuthConfig
 */
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception { 
            http
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/", "/gallery", "/album", "/photo", "/photographer", "/cart").permitAll()
            .antMatchers(HttpMethod.GET, "/dashboard").hasAnyAuthority("EMPLOYEE")
            .and()
            .formLogin()
            .defaultSuccessUrl("/dashboard")
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 

    }

    @Bean
    private DataSource buildDatasource() {  
        DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.driverClassName(
        environment.getProperty("spring.datasource.driver-class-name"));
        dataSource.url(environment.getProperty("spring.datasource.url"));
        dataSource.username(environment.getProperty("spring.datasource.username"));
        dataSource.password(environment.getProperty("spring.datasource.password"));
        return dataSource.build();
    }
    
}