package ru.kurma.security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.kurma.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.kurma.service")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final AuthenticationSuccessHandler successHandler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, AuthenticationSuccessHandler successHandler) {
        this.userDetailsService = userDetailsService;
        this.successHandler = successHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/auth","/glogin", "/signin", "/login", "/css/**", "/error**").permitAll()
                .antMatchers("/admin/**").hasAuthority("admin")
                .anyRequest().authenticated()
                    .and()
                .formLogin().successHandler(successHandler)
                .loginPage("/signin")
                    .and()
                .logout().logoutSuccessUrl("/").permitAll()
                    .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
