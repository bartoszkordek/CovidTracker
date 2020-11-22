package com.project.aggregator.api.zuulapigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    @Autowired
    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, environment.getProperty("service.account.url.login")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("service.account.url.registration")).permitAll()
                .antMatchers(environment.getProperty("service.account.url.h2console")).permitAll()
                .antMatchers(environment.getProperty("service.local-covid-data-service.url")).permitAll()
                .antMatchers(environment.getProperty("service.covid19-tracking-narrativa-service")).permitAll()
                .antMatchers(environment.getProperty("service.search.url")).authenticated()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), environment));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
