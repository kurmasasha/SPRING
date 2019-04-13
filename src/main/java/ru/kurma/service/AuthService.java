package ru.kurma.service;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AuthService {

    private final String clientId = "94160325048-ulvnbpu7niq5fur7daobss0e2hpuveeq.apps.googleusercontent.com";
    private final String clientSecret = "fGBlgHfv-EVcqL4Kpuz1Cy30";
    private final String secretState = "secret" + new Random().nextInt(999_999);
    private Map<String, String> additionalParam;


    public void buildUrl(HttpServletRequest request, HttpServletResponse response) {

        OAuth20Service service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope("openid")
                .callback("http://localhost:8080/auth")
                .build(GoogleApi20.instance());

        additionalParam = new HashMap<>();
        additionalParam.put("access_type", "offline");
        additionalParam.put("prompt", "consent");

        String authorizationUrl = service.createAuthorizationUrlBuilder()
                .state(secretState)
                .additionalParams(additionalParam)
                .build();

        String code = request.getParameter("code");
        System.out.println(code);
    }

}
