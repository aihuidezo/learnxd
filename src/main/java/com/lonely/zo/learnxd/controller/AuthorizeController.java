package com.lonely.zo.learnxd.controller;

import com.lonely.zo.learnxd.dto.AccessTokenDTO;
import com.lonely.zo.learnxd.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @Author ZB
 * @Date 2019-08-01
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")  String code,
                           @RequestParam(name="state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_cecret("4969326dd1c95d434cb0");
        accessTokenDTO.setClient_cecret("603bfc048fc5fbf851f37ec5719792b014981e6d");
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        githubProvider.getAccessToken(accessTokenDTO);
        return "index";
    }
}
