package com.gruptd.medicPet.config;

import com.gruptd.medicPet.services.CustomUserDetailsService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
/**
 * *
 * @author pablomorante
 */
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/medicpet/**").hasRole("USER")
                    .requestMatchers("/**").permitAll()
                )
                .formLogin((form) -> form
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/medicpet/tractaments", true)
                )
                .rememberMe((remember) -> remember
                    .key("uniqueAndSecret")
                    .tokenValiditySeconds(604800)
                    .rememberMeParameter("remember-me")
                    .userDetailsService(userDetailsService)
                    .tokenRepository(tokenRepository(dataSource)))
                .logout((logout) -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout"))
                .exceptionHandling(x -> x.accessDeniedPage("/error404"))
                .csrf(); // Protecció contra atacs CSRF

        // protecció per evitar que els atacants robin la sessió d'un usuari canviant l'ID de la sessió
        http
                .sessionManagement()
                .sessionFixation().migrateSession();

        // protecció per evitar que els atacants incloguin l'aplicació en un iframe i enganyi els usuaris perquè facin clic als botons ocults
        http
                .headers()
                .frameOptions().sameOrigin();

        return http.build();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
