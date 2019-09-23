package org.huboy1.huboy1.controller;

import org.huboy1.huboy1.DTO.AccessTokenDTO;
import org.huboy1.huboy1.mapper.UserMapper;
import org.huboy1.huboy1.model.User;
import org.huboy1.huboy1.provider.GithubProvider;
import org.huboy1.huboy1.provider.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client_id}")
    private String client_id;

    @Value("${github.redirect_uri}")
    private String redirect_uri;

    @Value("${github.client_secret}")
    private String client_secret;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response

    ){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            System.out.println("nihao");
            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
           //System.out.println(userMapper.select());
            //登陆成功，写cookie，session
            request.getSession().setAttribute("user",githubUser);
           return "redirect:/";
        }else {
            //登陆失败，重新登陆
            return "redirect:index";
        }
    }
}
