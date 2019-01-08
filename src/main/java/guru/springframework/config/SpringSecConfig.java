
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
	private static final String ENCODED_PASSWORD_user = "$2a$10$hq29FpTaJmJ7ojpci3Y4seBMxSXN8Wt7kSvelcRtCljDMhK2EBqCq";
	private static final String ENCODED_PASSWORD_admin = "$2a$10$VjHHsfg65xgBQyEs2NYI..rkGUsw8uiSW9d5hd8nNgN22BRfxpECC";
	// password is secret123
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
		.withUser("user").password(ENCODED_PASSWORD_user).roles("USER").and()
		.withUser("admin").password(ENCODED_PASSWORD_admin).roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/css/**","/js/**", "/images/**", "/**/favicon.ico").permitAll()
				//.antMatchers("/login","/logout","/logout-success").permitAll()
			.and()
				.authorizeRequests()
					.anyRequest().authenticated()
		        .and()
					.formLogin()
						.loginPage("/login")
						.defaultSuccessUrl("/")
						.permitAll()
				.and()
					.logout()
						.deleteCookies("remove")
						.invalidateHttpSession(true)
						.logoutUrl("/logout")
						.logoutSuccessUrl("/logout-success")
						.permitAll()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}