package com.ckinan;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("cesar").password("{noop}s3cr3t").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomFilter mupaf = new CustomFilter();
        mupaf.setAuthenticationManager(authenticationManager());

        http
            .csrf().disable()
            .formLogin().disable()
            .addFilterAt(
                    mupaf,
                    UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .antMatchers(HttpMethod.GET, "/protected-resource").authenticated()
            .antMatchers(HttpMethod.POST, "/login").permitAll();
    }

}
