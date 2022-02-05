package com.example.mercury.controller;

import com.example.mercury.entitiy.Enterprise;
import com.example.mercury.service.DocumentService;
import com.example.mercury.service.EnterpriseService;
import com.example.mercury.service.MercuryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("enterprise/")
public class EnterpriseController {
    private final EnterpriseService enterpriseService;
    private final DocumentService documentService;
    private final MercuryService mercuryService;

    @Autowired
    public EnterpriseController(EnterpriseService enterpriseService, DocumentService documentService, MercuryService mercuryService) {
        this.enterpriseService = enterpriseService;
        this.documentService = documentService;
        this.mercuryService = mercuryService;
    }

    @GetMapping("/add")
    public String goToAddEntreprise() {
        return "AddEnterprise";
    }

    @PostMapping("/check")
    public String checkEntreprise(@RequestParam(name = "uuidString") String uuid,
                                  Enterprise enterprise,
                                  Model model) {

        model.addAttribute("uuid",uuid);
        model.addAttribute("enterprise",enterprise);
        model.addAttribute("isVerifed",mercuryService.isEnterpriseVerifed(enterprise,uuid));
        model.addAttribute("mercuryName",mercuryService.getEnterpriseName(enterprise,uuid));

        return "AddEnterpriseValidateAndCommit";
    }

    @PostMapping("/add")
    public String addEntreprise(@RequestParam(name = "uuidString") String uuid,
                                @RequestParam(name = "verived") boolean verived,
                                Enterprise enterprise) {

        if (verived) {
            enterpriseService.addEnterprise(enterprise,uuid,SecurityContextHolder.getContext().getAuthentication().getName());
        }

        return "redirect:/";
    }

    @GetMapping("{uuid}")
    public String getEntreprisePage(@PathVariable(name = "uuid") String uuid, Model model) {
        Enterprise enterprise = enterpriseService.getByUUID(uuid);
        model.addAttribute("viewAllDocs",false);
        model.addAttribute("enterprise",enterprise);
        model.addAttribute("documentList",documentService.getNotProcessedByEnterprise(enterprise));

        return "Enterprise";
    }

    @GetMapping("{uuid}/allDocs")
    public String getEntrepriseAllDocsPage(@PathVariable(name = "uuid") String uuid, Model model) {
        Enterprise enterprise = enterpriseService.getByUUID(uuid);
        model.addAttribute("viewAllDocs",true);
        model.addAttribute("enterprise",enterprise);
        model.addAttribute("documentList",documentService.getAllByEnterprise(enterprise));

        return "Enterprise";
    }

    @PostMapping("{uuid}/processAllDocs")
    public String processtEntrepriseDocs(@PathVariable(name = "uuid") String uuid) {
        Enterprise enterprise = enterpriseService.getByUUID(uuid);
        mercuryService.processAllDocumentByEnterprise(enterprise,null, null);

        return "redirect:/enterprise/"+uuid;
    }
}
