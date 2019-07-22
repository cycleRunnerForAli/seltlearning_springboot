package com.cyclerunner.nevermore.IndexController;

import com.cyclerunner.nevermore.dto.AccessTokenDTO;
import com.cyclerunner.nevermore.dto.GitHubUser;
import com.cyclerunner.nevermore.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("Iv1.5602739ed0ac98c9");
        accessTokenDTO.setClient_secret("31a307410778ddf39600cdf06d26f1100bff0718");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser = githubProvider.getGitHubUser(accessToken);
        System.out.println(gitHubUser.getName());
        return "index";
    }
}
