package com.cyclerunner.nevermore.IndexController;

import com.cyclerunner.nevermore.dto.AccessTokenDTO;
import com.cyclerunner.nevermore.dto.GitHubUser;
import com.cyclerunner.nevermore.mapper.UserMapper;
import com.cyclerunner.nevermore.model.User;
import com.cyclerunner.nevermore.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.redirectUri}")
    private String clientRedirectUri;


    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request, HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(clientRedirectUri);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = githubProvider.getGitHubUser(accessToken);
        if (gitHubUser != null) {
            User user = new User();
            user.setName(gitHubUser.getName());
            user.setAccountId(gitHubUser.getId());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setAvatarUrl(gitHubUser.getAvatar_url());
            userMapper.insert(user);
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
