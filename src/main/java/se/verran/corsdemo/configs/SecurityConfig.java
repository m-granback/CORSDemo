package se.verran.corsdemo.configs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf)->csrf.disable()); // Inactivate CSRF-protection
        http.authorizeHttpRequests(auth->auth.anyRequest().permitAll());

                http
                .cors(c->{
                    CorsConfigurationSource ccs = request-> {
                        CorsConfiguration cc = new CorsConfiguration();
                        cc.setAllowedOrigins(List.of("http://127.0.0.1:8080", "http://localhost:8080"/*, "null"*/));
                        cc.setAllowedMethods(List.of("POST", "GET", "OPTIONS"));
                        cc.setAllowedHeaders(List.of("*"));
                        cc.setAllowCredentials(true);
                        return cc;
                    };
                    c.configurationSource(ccs);
                });

        return http.build();
    }

}
