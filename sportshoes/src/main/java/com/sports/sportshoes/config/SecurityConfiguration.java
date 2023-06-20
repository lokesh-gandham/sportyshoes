package com.sports.sportshoes.config;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	//Properties
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserDetailsService userDetailsService;	
	
	@Autowired
	private AccessDeniedHandler customAccessDeniedHandler;
	
	//Methods
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		System.out.println("dataSource = " + dataSource);
		auth.userDetailsService(userDetailsService); //called to lookup a user from the database
		System.out.println(auth.userDetailsService(userDetailsService));		
	}		
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		 

		http.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/home").permitAll()
				.antMatchers("http://localhost:8080/swagger-ui.html/**").permitAll()
				.antMatchers("/api/admin/**").hasAuthority("ADMIN")
		        .antMatchers("/admin").hasAuthority("ADMIN")
				.antMatchers("/user").hasAuthority("USER")
				.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")				
				.and()
					.csrf().disable()				
					.logout()					
					.logoutSuccessHandler(
							new LogoutSuccessHandler() {					     
					    @Override
					    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					                Authentication authentication)
					            throws IOException, ServletException {
					         
					        System.out.println("This user logged out: " + authentication.getName());
					         
					        response.sendRedirect("/login");
					    }
					})
					.invalidateHttpSession(true)
				.and()
					.exceptionHandling()
					.accessDeniedHandler(customAccessDeniedHandler);		
	}	
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		//Allows static	content, such as CSS and images, to be excluded from authentication
		web.ignoring().antMatchers("/webjars/**", "/images/**", "/css/**");
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
		System.out.println("Inside bCryptPasswordEncoder()..");
        return new BCryptPasswordEncoder();
    }	
	
}//end class
