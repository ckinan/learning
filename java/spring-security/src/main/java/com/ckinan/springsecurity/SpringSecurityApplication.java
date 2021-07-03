package com.ckinan.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@SpringBootApplication
public class SpringSecurityApplication {

	@RequestMapping("/resource")
	public Map<String,Object> resource() {
		Map<String,Object> model = new HashMap<>();
		model.put("id", UUID.randomUUID().toString());
		model.put("content", "Hello World, I'm a protected resource");
		return model;
	}

	@RequestMapping("/me")
	public Map<String,Object> me() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		Map<String,Object> model = new HashMap<>();
		model.put("username", authentication.getName());
		model.put("principal", authentication.getPrincipal());
		model.put("authorities", authentication.getAuthorities());
		return model;
	}

	@EnableWebSecurity
	//@Profile("basic-auth")
	protected static class SecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder builder) throws Exception {
			builder.inMemoryAuthentication()
                    .withUser("user")
                    .password("{noop}pass")
					.roles("USER")
                    .and()
                    .withUser("admin")
                    .password("{noop}pass")
                    .roles("USER", "ADMIN");
        }

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			CustomUsernamePasswordAuthenticationFilter cupaf = new CustomUsernamePasswordAuthenticationFilter();
			cupaf.setAuthenticationManager(authenticationManager());
			http
				//.csrf().csrfTokenRepository(new CookieCsrfTokenRepository() | HttpSessionCsrfTokenRepository)
				.addFilterBefore(
						cupaf,
						UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
				.formLogin().disable()
				.httpBasic().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/resource").hasRole("USER")
				.antMatchers(HttpMethod.GET, "/me").hasRole("USER")
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.anyRequest().authenticated();
		}
	}

	protected static class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

		@Override
		public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					"user", "pass");

			// Allow subclasses to set the "details" property
			setDetails(request, authRequest);

			return this.getAuthenticationManager().authenticate(authRequest);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
