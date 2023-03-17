package com.gruptd.medicPet.config;

import com.gruptd.medicPet.models.Rol;
import com.gruptd.medicPet.services.CustomUserDetailsService;
import java.util.Collection;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
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
                    response.sendRedirect("/medicpet/tractaments");
                    })
                )
                .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/registre").hasAuthority("ADMIN")
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
                    .authenticationEntryPoint(authenticationEntryPoint())
                )
                .logout((logout) -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout"))
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
    
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
}
