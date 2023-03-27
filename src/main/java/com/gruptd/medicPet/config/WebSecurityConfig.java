package com.gruptd.medicPet.config;

import com.gruptd.medicPet.services.CustomUserDetailsService;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * La classe WebSecurityConfig proporciona la configuració de Spring Security. Configura els mecanismes d'autorització i autenticació.
 *
 * @author pablomorante
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @Autowired
    private DataSource dataSource;

    /**
     * Configura la SecurityFilterChain per a l'aplicació.
     * 
     * @param http l'objecte HttpSecurity utilitzat per configurar la SecurityFilterChain
     * @return objecte SecurityFilterChain configurat
     * @throws Exception si hi ha un error en configurar la SecurityFilterChain
     * @author pablomorante
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {        
        http
                .formLogin((form) -> form
                    .loginPage("/login")
                    .permitAll()
                    .successHandler((request, response, auth) -> {
                        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
                        for (GrantedAuthority authority : authorities) {
                            System.out.println(authority.getAuthority());
                            if (authority.getAuthority().equals("ADMIN")) {
                                response.sendRedirect("/registre");
                                return;
                            }
                        }
                    response.sendRedirect("/medicpet/clients");
                    })
                )
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/registre").hasAuthority("ADMIN")
                    .requestMatchers("/medicpet/tractaments/fitxa", "/medicpet/tractaments/eliminar/**", "/medicpet/tractaments/guardar").hasAuthority("ADMINISTRATIU")
                    .requestMatchers("/medicpet/rrhh/fitxa", "/medicpet/rrhh/eliminar/**", "/medicpet/rrhh/guardar").hasAuthority("ADMINISTRATIU")
                    .requestMatchers("/medicpet/**").hasAnyAuthority("ADMINISTRATIU", "VETERINARI")
                    .requestMatchers("/**").permitAll()
                )
                .rememberMe((remember) -> remember
                    .key("uniqueAndSecret")
                    .tokenValiditySeconds(604800)
                    .rememberMeParameter("remember-me")
                    .userDetailsService(userDetailsService)
                    .tokenRepository(tokenRepository(dataSource)))
                .exceptionHandling( (ex) -> ex
                    .accessDeniedHandler(accessDeniedHandler())
//                    .authenticationEntryPoint(authenticationEntryPoint())
                )
                .logout((logout) -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?message=logout"))
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

    /**
     * Configura l'AuthenticationManagerBuilder amb un UserDetailsService i un PasswordEncoder.
     * 
     * @param auth l'AuthenticationManagerBuilder que s'ha de configurar
     * @throws Exception si hi ha un error en configurar AuthenticationManagerBuilder
     * @author pablomorante
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Retorna una nova instància de BCryptPasswordEncoder que implementa la interfície PasswordEncoder. S'utilitza per codificar les contrasenyes
     * d'usuaris per a l'emmagatzematge segur en una base de dades. 
     * 
     * @return una nova instància de BCryptPasswordEncoder
     * @author pablomorante
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * Retorna una nova instància de JdbcTokenRepositoryImpl que implementa la interfície PersistentTokenRepository. 
     * S'utilitza per persistir remember-me tokens per a la sessió de l'usuari en la base de dades.
     * 
     * @param dataSource la font de dades que proporciona la connexió a la base de dades
     * @return nova instància de JdbcTokenRepositoryImpl
     * @author pablomorante
     */
    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    /**
     * Retorna una nova instància de CustomAccessDeniedHandler que implementa la interfície AccessDeniedHandler.
     * Accedeix a excepcions denegades redirigint-te a una pàgina d'error personalitzada.
     * 
     * @return una nova instància de CustomAccessDeniedHandler.
     * @author pablomorante
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}