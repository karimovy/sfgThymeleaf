
package guru.springframework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecConfig extends WebSecurityConfigurerAdapter {

	private static final String ENCODED_PASSWORD = "$2a$10$AIUufK8g6EFhBcumRRV2L.AQNz3Bjp7oDQVFiO5JJMBFZQ6x2/R/2";
	// password is secret123
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder()).withUser("user").password(ENCODED_PASSWORD)
		.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
/*
		http
        .authorizeRequests()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .and()
        .httpBasic();
*/		
		
	//	http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll();
		http
			.authorizeRequests()
				.antMatchers("/css/**","/js/**", "/images/**", "/**/favicon.ico").permitAll()
				.antMatchers("/thymeleaf/login","/thymeleaf/logout","/thymeleaf/logout-success").permitAll()
			.and()
				.authorizeRequests()
					.anyRequest().authenticated()
		        .and()
					.formLogin()
						.loginPage("/thymeleaf/login")
						.defaultSuccessUrl("/thymeleaf/")
						.permitAll()
				.and()
					.logout()
						.deleteCookies("remove")
						.invalidateHttpSession(true)
						.logoutUrl("/thymeleaf/logout")
						.logoutSuccessUrl("/thymeleaf/logout-success")
					.logoutRequestMatcher(new AntPathRequestMatcher("/thymeleaf/logout"))
					;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}