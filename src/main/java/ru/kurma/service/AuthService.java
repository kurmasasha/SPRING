package ru.kurma.service;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kurma.dao.RoleDao;
import ru.kurma.model.Role;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class AuthService {

    private final RoleDao roleDao;

    private final String secretState = "secret" + new Random().nextInt(999_999);
    private OAuth20Service service;

    @Autowired
    public AuthService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public String buildUrl() {
        service = new ServiceBuilder("94160325048-ulvnbpu7niq5fur7daobss0e2hpuveeq.apps.googleusercontent.com")
                .apiSecret("fGBlgHfv-EVcqL4Kpuz1Cy30")
                .defaultScope("profile")
                .callback("http://localhost:8080/auth")
                .build(GoogleApi20.instance());
        Map<String, String> additionalParam = new HashMap<>();
        additionalParam.put("access_type", "offline");
        additionalParam.put("prompt", "consent");

        return service.createAuthorizationUrlBuilder()
                .state(secretState)
                .additionalParams(additionalParam)
                .build();
    }

    public void auth(String code) {
        OAuth2AccessToken accessToken = null;
        try {
            accessToken = service.getAccessToken(code);
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v3/userinfo?fields=");
        service.signRequest(accessToken, request);
        Response response = null;
        try {
            response = service.execute(request);
        } catch (InterruptedException | ExecutionException | IOException e) {
            e.printStackTrace();
        }

        String body = null;

        try {
            assert response != null;
            body = response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert body != null;
        JSONObject jsonObject = new JSONObject(body);

        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.findRoleById(2));

        login(jsonObject.getString("given_name"), jsonObject.getString("family_name"), roles);
    }

    public void login(String user, String pass, Set<Role> roles) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(user, pass, roles);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authReq);
    }
}
