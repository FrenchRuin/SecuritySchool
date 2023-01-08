package com.example.securitySchool.config;

import com.example.securitySchool.user.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class OnlinePaperSecurityConfig {

        private final UserSecurityService userSecurityService;

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                final SpLoginFilter filter = new SpLoginFilter(

                )

                return http
                        .csrf().disable()
                        .formLogin(login->{
                                login.loginPage("/login")
                                ;
                        })
                        .logout(logout->{
                                logout.logoutSuccessUrl("/")
                                ;
                        })
                        .rememberMe(config->{
                                config.rememberMeServices(rememberMeServices())
                                ;
                        })
                        .addFilterAt(filter, UsernamePasswordAuthenticationFilter.class)
                        .exceptionHandling(exception->{
                                exception.accessDeniedPage("/access-denied");
                        })
                        .authorizeRequests(config->{
                                config
                                        .antMatchers("/").permitAll()
                                        .antMatchers("/login").permitAll()
                                        .antMatchers("/error").permitAll()
                                        .antMatchers("/signup/*").permitAll()
                                        .antMatchers("/study/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_STUDENT")
                                        .antMatchers("/teacher/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER")
                                        .antMatchers("/manager/**").hasAuthority("ROLE_ADMIN")
                                ;
                        })
                        ;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
                return authenticationConfiguration.getAuthenticationManager();
        }

        private RememberMeServices rememberMeServices() {
                TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(
                        "paper-site-remember-me"
                        , userSecurityService
                );
                rememberMeServices.setParameter("remember-me");
                rememberMeServices.setAlwaysRemember(true);
                rememberMeServices.setTokenValiditySeconds(3600);
                return rememberMeServices;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }


}
