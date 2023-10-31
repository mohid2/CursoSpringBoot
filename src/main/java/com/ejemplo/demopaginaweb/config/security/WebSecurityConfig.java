package com.ejemplo.demopaginaweb.config.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private UserDetailsService userDetailsService;
    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    //@Bean
//    public UserDetailsService userDetailsService(){
//        PasswordEncoder passwordEncoder= PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return new InMemoryUserDetailsManager(
//                User.withUsername("moha")
//                        .password(passwordEncoder().encode("moha"))
//                        .roles("ADMIN","USER")
//                        .build(),
//                User.withUsername("sofia")
//                        .password(passwordEncoder().encode("moha"))
//                        .roles("USER")
//                        .build()
//        );
//    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    String[] resources = new String[]{"/css/**", "/js/**", "/img/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) ->
                        authz.requestMatchers(resources).permitAll()
                                .requestMatchers("/listar","/","/api/clientes/**","/registro").permitAll()
                                .requestMatchers("/uploads/**").hasAnyRole("USER")
                                .requestMatchers("/ver/**").hasRole("USER")
                                .requestMatchers("/factura/**").hasRole("ADMIN")
                                .requestMatchers("/form/**").hasRole("ADMIN")
                                .requestMatchers("/eliminar/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/listar")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login")
                        .passwordParameter("password")
                        .usernameParameter("username")
                        .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());

        return http.build();

    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
