package ru.kurma;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.kurma.config.UserConfig;
import ru.kurma.model.User;

public class UserApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(UserConfig.class);

        User user = context.getBean(User.class);

        user.setName("Sasha");

        String name = user.getName();

        System.out.println(name);
    }
}
