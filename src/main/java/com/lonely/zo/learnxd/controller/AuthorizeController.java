package com.lonely.zo.learnxd.controller;

import com.lonely.zo.learnxd.dto.AccessTokenDTO;
import com.lonely.zo.learnxd.dto.GithubUser;
import com.lonely.zo.learnxd.mapper.UserMapper;
import com.lonely.zo.learnxd.model.User;
import com.lonely.zo.learnxd.provider.GithubProvider;
import com.lonely.zo.learnxd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Description
 * @Author ZB
 * @Date 2019-08-01
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserService userService;

    @Value("4969326dd1c95d434cb0")
    private String clientId;
    @Value("7c76093d695494957a81a7eeaf7ca9eba54acc7b")
    private String clientSecret;
    @Value("http://localhost:8887/callback")
    private String redirectUri;

    private GithubUser githubUser;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")  String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        //创建accessTokenDTO对象，并初始化
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setCode(code);
        //获取AccessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //获得github用户信息
        githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null && githubUser.getId() != null){
                User user = new User();
                String token = UUID.randomUUID().toString();
                user.setToken(token);
                user.setName(githubUser.getName());
                user.setAccountId(String.valueOf(githubUser.getId()));
                user.setAvatarUrl(githubUser.getAvatarUrl());
                user.setBio(githubUser.getBio());
                userService.createOrUpdata(user);
                response.addCookie(new Cookie("token",token));
                return "redirect:/";
        }else{
            //登陆失败，重新登录
            return "redirect:/";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
