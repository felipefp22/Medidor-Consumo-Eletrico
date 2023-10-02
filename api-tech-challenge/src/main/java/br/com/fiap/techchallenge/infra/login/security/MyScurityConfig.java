package br.com.fiap.techchallenge.infra.login.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MyScurityConfig {

    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)                  //aqui desabilita aquele senha e usuario padrao do spring.security
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))          // statless é que funciona com usuario precisando de um token para comunicar
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers(toH2Console()).permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuario/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/endereco" ).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/teste").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/teste").hasRole("ADMIN")     // aqui digo quais caminhos quero que tenha role especifico
                        .anyRequest().authenticated())         //aqui diz que o resto das requisiçoes nao precisao de role especifica, apenas autenticado ja consegue comunicar
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
