package pe.edu.upc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSuccessHandler;
import pe.edu.upc.service.impl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	protected void configure(HttpSecurity http) throws Exception{
		try {
			http.authorizeRequests().antMatchers("/css/**", "/img/**","/static/**","/user/registrarEstudiante","/user/irRegistrarEmpresario","/user/irRegistrarEstudiante","/user/listar").permitAll().anyRequest()
			.authenticated().and().formLogin()
			.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/post/listar")
			.and().logout().permitAll().and()
			.exceptionHandling().accessDeniedPage("/error");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception{
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
