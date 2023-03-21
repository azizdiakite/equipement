package org.itech.equipment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user@itech-civ.org").password(bCryptPasswordEncoder.encode("!Mot_de_passe"))
		.roles("USER").and().withUser("admin@itech-civ.org").password(bCryptPasswordEncoder.encode("P@ssword"))
		.roles("ADMIN");
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/", "/home", "/dashboard", "/dashboard/**", "/about", "/webjars/**", "/DataTables/**",
						"/css/**", "/js/**", "/img/**")
				.permitAll().antMatchers("/admin/**").hasAnyRole("ADMIN").antMatchers("/static/*").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll().and()
				.logout().logoutSuccessUrl("/").permitAll().and().exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
		//.antMatchers("/user/**").hasAnyRole("USER")
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**", "/content/**", "/csrf", "/css/**", "/js/**", "/img/**")
				.antMatchers("/error/**").antMatchers("/error").antMatchers("/**/*.{js,html,css,map,ico}")
				.antMatchers("/resources/**");
	}
}
