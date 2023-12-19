package com.project.shoppingmall.controller;

import com.project.shoppingmall.domain.Users;
import com.project.shoppingmall.dto.UserDTO;
import com.project.shoppingmall.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    private final UsersService usersService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public LoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/signUp")
    public String signUp() {

        return "/signUp";
    }

    @GetMapping("/signUpInput")
    public String signUpInput() {

        return "redirect:/signUp";
    }

    @PostMapping("/signUpComp")
    public String signUpComp(@Valid UserDTO userDTO, BindingResult bindingResult,  @RequestParam String pwd) {
        if (bindingResult.hasErrors()) {

            return "/signupError";
        }
        String encPwd = bCryptPasswordEncoder.encode(pwd);
        userDTO.setPwd(encPwd);
        Users users = new Users(userDTO.getUserId(), encPwd, userDTO.getName(), userDTO.getPostcode(), userDTO.getAddress1(), userDTO.getAddress2(), userDTO.getPhone(), userDTO.getEmail(), userDTO.getBirth());
        usersService.saveUser(users);

        return "/loginForm";
    }

    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }

    @PostMapping("/loginComp")
    public String loginComp(@RequestParam("userId") String userId, @RequestParam("pwd") String pwd, HttpSession session) {
        if(usersService.loginUserId(userId) == 1 && usersService.loginUserPwd(pwd) > 1) {
            session.setAttribute("userId", userId);

            return "redirect:/main";
        } else {

            return "loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "/main";
    }
}
