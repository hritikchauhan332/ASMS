package com.school.management.configuration.security;

import com.school.management.configuration.exception.ExceptionHandlerFilter;
import com.school.management.configuration.jwt.JWTAuthenticationFilter;
import com.school.management.configuration.jwt.JWTTokenHelper;
import com.school.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Autowired
    JWTTokenHelper jwtTokenHelper;

    @Autowired
    AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/auth/login");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().permitAll().and().csrf().disable();;

//        http
//                .csrf().disable()
//                .cors().disable()
//                .authorizeRequests().anyRequest().authenticated();
        //authorizeRequests((request) -> request.antMatchers( HttpMethod.OPTIONS,"/auth/login").permitAll().antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()).
        http.sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().
                exceptionHandling().
                authenticationEntryPoint(authenticationEntryPoint).
                and().
                authorizeRequests().
                antMatchers("/auth/login").permitAll().
                antMatchers("/teacher/**").authenticated().
                antMatchers("/session/**").authenticated();

        http.addFilterBefore(new JWTAuthenticationFilter(userService, jwtTokenHelper), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new ExceptionHandlerFilter(), LogoutFilter.class);

        http.csrf().disable().cors().and().headers().frameOptions().disable();
    }
}
