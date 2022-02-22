package com.cardgame.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	//Endpoint publico, primeiro a rota de login
	private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"};
	
	private static final String[] PLAYER_OR_ADMIN = {"/quiz/**", "/rank/**"};
	
	private static final String[] ADMIN = {"/users/**"};
	
	//ele recebe e analisa se ta batendo com os dados 
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(tokenStore);
	}
	
	//vamos configurar as rotas
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		//h2
		if(Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll()
		.antMatchers(PLAYER_OR_ADMIN).permitAll()
		.antMatchers(PLAYER_OR_ADMIN).hasAnyRole("PLAYER", "ADMIN")
		.antMatchers(ADMIN).hasRole("ADMIN")
		.anyRequest().authenticated();
	}
	
	
	
}
