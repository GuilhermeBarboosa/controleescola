package web.projetoescola;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	
	//Guilherme Barbosa Rocha
	
	@Autowired
	private DataSource dataSource;
	
    @Bean
 	public UserDetailsService userDetailsService() {    	
          JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
          manager.setUsersByUsernameQuery("select nome_usuario, senha, ativo "
        		  						+ "from usuario "
        		  						+ "where nome_usuario = ?");
          manager.setAuthoritiesByUsernameQuery(
        		  "SELECT tab.nome_usuario , papel.nome FROM"
        		+ "(SELECT usuario.nome_usuario , usuario.codigo FROM usuario WHERE nome_usuario = ?) as tab "
        		+ " INNER JOIN usuario_papel ON codigo_usuario = tab.codigo "
        		+ " INNER JOIN papel ON codigo_papel = papel.codigo;");
          return manager;
	}
    
	@Bean
	public PasswordEncoder passwordEncoder() {
		Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
		return passwordEncoder;
	}
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http
		.authorizeRequests()
		.antMatchers("/aluno/notas").hasAnyRole("PROFESSOR", "ADMIN")
		.antMatchers("/aluno/relatorio").hasRole("ADMIN")
		.antMatchers("/aluno/remover").hasAnyRole("SECRETARIA", "ADMIN")
		.antMatchers("/aluno/cadastrar").hasAnyRole("ADMIN", "SECRETARIA")
		.antMatchers("/aluno/alterar").hasAnyRole("ADMIN", "SECRETARIA")	
		.antMatchers("/aluno/buscar").hasAnyRole("ADMIN", "SECRETARIA")
			.anyRequest().authenticated()
			.and() 
		.formLogin()
			.defaultSuccessUrl("/")
			.and()
				.logout()
				.logoutSuccessUrl("/");
		
		return http.build();
    }

}
