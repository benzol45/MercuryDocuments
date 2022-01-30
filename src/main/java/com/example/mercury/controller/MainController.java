package com.example.mercury.controller;

import com.example.mercury.entitiy.User;
import com.example.mercury.service.EnterpriseService;
import com.example.mercury.service.MercuryService;
import com.example.mercury.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String getIndexPage(Model model, Authentication authentication) {
        User user = getCurrentUser(authentication);

        model.addAttribute("fullName",user.getFullName());
        model.addAttribute("enterpriseDocumentList",enterpriseService.getListNumerDocumentOfEnterpriseByUser(user));
        return "Main";
    }

    @GetMapping("/getAllDocumets")
    public String getAllDocumentFromMercury(Authentication authentication) {
        mercuryService.addNewDocumentFromMercuryToBaseByUSer(getCurrentUser(authentication));

        return ("redirect:/");
    }

    @GetMapping("/processAllDocumets")
    public String processUserDocs(Authentication authentication) {
        mercuryService.processAllDocumentByUser(getCurrentUser(authentication));

        return ("redirect:/");
    }

    private User getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username).get();

        return user;
    }

}
