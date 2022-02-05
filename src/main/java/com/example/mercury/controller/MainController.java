package com.example.mercury.controller;

import com.example.mercury.entitiy.User;
import com.example.mercury.service.EnterpriseService;
import com.example.mercury.service.MercuryService;
import com.example.mercury.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class MainController {
    private final EnterpriseService enterpriseService;
    private final MercuryService mercuryService;
    private final UserService userService;

    @Autowired
    public MainController(EnterpriseService enterpriseService, MercuryService mercuryService, UserService userService) {
        this.enterpriseService = enterpriseService;
        this.mercuryService = mercuryService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String getIndexPage(Model model, Authentication authentication, HttpServletResponse response) {
        User user = getCurrentUser(authentication);

        model.addAttribute("fullName",user.getFullName());
        model.addAttribute("enterpriseDocumentList",enterpriseService.getListNumerDocumentOfEnterpriseByUser(user));

        Cookie cookie = new Cookie("notificationID", UUID.randomUUID().toString());
        cookie.setMaxAge(-1);
        response.addCookie(cookie);

        return "Main";
    }

    @GetMapping("/getAllDocumets")
    public String getAllDocumentFromMercury(Authentication authentication) {
        mercuryService.getAndSaveDocumentFromMercuryToBaseByUSer(getCurrentUser(authentication));

        return ("redirect:/");
    }

    @GetMapping("/processAllDocumets")
    public String processUserDocs(Authentication authentication, @CookieValue(name = "notificationID") String notificationID) {
        mercuryService.processAllDocumentByUser(getCurrentUser(authentication), notificationID);

        return ("redirect:/");
    }

    private User getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username).get();

        return user;
    }

}
