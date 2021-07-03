package com.ckinan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SpringSecurity02Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity02Application.class, args);
	}

	@EnableWebSecurity
	static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
					.formLogin()
						.loginPage("/custom-login")
						.usernameParameter("myUser")
						.passwordParameter("myPass")
						.defaultSuccessUrl("/protected-resource")
						.failureHandler(new SimpleUrlAuthenticationFailureHandler("/login-error"))
					.and()
					.logout()
						.logoutUrl("/custom-logout")
						.logoutSuccessUrl("/")
					.and()
					.authorizeRequests()
						.antMatchers(HttpMethod.GET, "/protected-resource").authenticated();
		}

	}

	@Controller
	static class MyController {
		@RequestMapping("/custom-login")
		public String login() {
			return "login";
		}

		@RequestMapping("/login-error")
		public String loginError() {
			return "login-error";
		}

		@RequestMapping("/protected-resource")
		public String protectedResource() {
			return "protected-resource";
		}

		@RequestMapping("/")
		public String publicResource() {
			return "public-resource";
		}
	}
}
