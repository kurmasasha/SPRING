package ru.kurma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kurma.model.User;

@Configuration
public class UserConfig {

    @Bean
    public User getUser() {
        return new User();
    }
}
