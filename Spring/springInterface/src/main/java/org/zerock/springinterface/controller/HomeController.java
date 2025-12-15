package org.zerock.springinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.springinterface.service.BlogService;
import org.zerock.springinterface.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/index.do"})
    public String home(Model model) {
        int totalBlogCount = blogService.getTotalBlogCount();
        int totalUserCount = userService.getTotalUserCount();

        model.addAttribute("totalBlogCount", totalBlogCount);
        model.addAttribute("totalUserCount", totalUserCount);
        return "index";
    }
}
