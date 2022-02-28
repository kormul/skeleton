package com.kormul.skeleton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	 	@Autowired
	    private UserDetailsService userDetailsService;
		
		@Bean
		public PasswordEncoder encoder() {
			return new BCryptPasswordEncoder();
		}
		
	 	@Override
	 	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(encoder());
	    }
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			
			http.httpBasic().disable();
			
			http.csrf().disable().authorizeRequests()
			.antMatchers("/user/**").hasAuthority("ADMIN")
			.antMatchers("/bidList/**", "/curvePoint/**", "/rating/**", "/ruleName/**", "/trade/**").authenticated()
			.antMatchers("/","/rest","/css/**").permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login_submit")
			.defaultSuccessUrl("/", true)
			.failureUrl("/login?error")
			.and()
			.logout()
			.logoutUrl("/app-logout").permitAll()
			.and()
			.exceptionHandling()
			.accessDeniedPage("/forbidden");
		}
}
