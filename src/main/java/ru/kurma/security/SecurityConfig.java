package ru.kurma.security;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/glogin", "/signin", "/login", "/css/**", "/error**").permitAll()
                .antMatchers("/admin/**").hasAuthority("admin")
                .anyRequest().authenticated()
                    .and()
                .formLogin().successHandler(successHandler)
                .loginPage("/signin")
                .usernameParameter("login")
                    .and()
                .logout().logoutSuccessUrl("/").permitAll()
                    .and()
                .csrf().disable();
    }



//    @Bean
//    public PrincipalExtractor principalExtractor(UserGooleRepository userGooleRepository) {
//        return map -> {
//            String id = (String) map.get("sub");
//
//            UserGoggle userGoggle = userGooleRepository.findById(id).orElseGet(() -> {
//                UserGoggle newUserGoggle = new UserGoggle();
//                newUserGoggle.setId(id);
//                newUserGoggle.setName((String) map.get("name"));
//                newUserGoggle.setEmail((String) map.get("email"));
//                newUserGoggle.setUserpic((String) map.get("picture"));
//                return newUserGoggle;
//            });
//            return userGooleRepository.save(userGoggle);
//        };
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
