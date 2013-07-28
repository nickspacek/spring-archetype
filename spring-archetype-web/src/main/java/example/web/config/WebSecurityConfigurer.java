package example.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth)
			throws Exception {
		// auth.authenticationProvider(alternativeAuthenticationProvider());
		auth.inMemoryAuthentication().withUser("user").password("password")
				.roles("USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeUrls().antMatchers("/register", "/").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN").anyRequest()
				.authenticated().and().formLogin().loginUrl("/login")
				.permitAll();
	}

	// @Bean
	// public AuthenticationProvider alternativeAuthenticationProvider() {
	// // Implement a custom AuthenticationProvider here (Yodlee, SocialCloud,
	// // etc.)
	// }
}
