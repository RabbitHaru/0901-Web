package org.zerock.springbookblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springbookblog.domain.dto.UserDTO;
import org.zerock.springbookblog.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 1. 로그인 폼
    @GetMapping("/login.do")
    public String loginForm() {
        return "user/login";
    }

    // 2. 로그인 처리
    @PostMapping("/login.do")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {

        UserDTO user = userService.loginUser(username, password);
        if (user != null) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("nickname", user.getNickname());
            session.setAttribute("user", user);
            redirectAttributes.addFlashAttribute("msg", "login_success");
            return "redirect:/index.do";
        } else {
            // addFlashAttribute 사용 (redirect 후에도 유지)
            redirectAttributes.addFlashAttribute("error", "invalid_credentials");
            return "redirect:/login.do";  // redirect로 변경
        }
    }

    // 3. 로그아웃
    @GetMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.do?msg=logout_success";
    }

    // 4. 회원가입 폼
    @GetMapping("/join.do")
    public String joinForm() {
        return "user/join";
    }

    // 5. 회원가입 처리
    @PostMapping("/join.do")
    public String join(UserDTO userDTO,
                       RedirectAttributes redirectAttributes) {

        boolean success = userService.joinUser(userDTO);
        if (success) {
            redirectAttributes.addFlashAttribute("msg", "join_success");
            return "redirect:/login.do";
        } else {
            redirectAttributes.addFlashAttribute("error", "join_failed");
            return "redirect:/join.do";
        }
    }
}