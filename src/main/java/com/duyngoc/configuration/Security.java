package com.duyngoc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.duyngoc.service.UserDetailsServices;

@Configuration
@EnableWebSecurity

public class Security extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServices userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// .antMatchers("/api/search/**").permitAll()
				// .antMatchers("/api/user").permitAll()
				// .antMatchers("/api/employee").access("hasRole('EMPLOYEE')||hasRole('ADMIN')")
				// .antMatchers("/api/**").access("hasRole('ADMIN')")

				.antMatchers("/api/public/**").permitAll()
				.antMatchers("/api/private/**").permitAll()
				.and().httpBasic().and().logout().logoutUrl("/logout").clearAuthentication(true)
				.invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll().logoutSuccessUrl("/").and().csrf()
				.disable();

	}

}
