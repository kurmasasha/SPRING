package ru.kurma.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import ru.kurma.security.AuthenticationProviderImpl;

@Configuration
@EnableWebSecurity
@ComponentScan("ru.kurma.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderImpl authenticationProvider;

    @Autowired
    public SecurityConfig(AuthenticationProviderImpl authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/","/signup", "/signin").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/**").hasAnyRole("admin", "user")
                .and()
                .formLogin()
                .loginPage("/signin")
                .usernameParameter("login")
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/login").permitAll()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }


}
