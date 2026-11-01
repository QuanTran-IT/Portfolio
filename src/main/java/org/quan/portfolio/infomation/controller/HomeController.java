package org.quan.portfolio.infomation.controller;


import jakarta.validation.Valid;
import org.quan.portfolio.infomation.config.ProfileProperties;
import org.quan.portfolio.infomation.dto.ContactForm;

import org.quan.portfolio.infomation.service.ContactService;
import org.quan.portfolio.infomation.service.ProjectService;
import org.quan.portfolio.infomation.service.SkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller

public class HomeController {
    private final ProfileProperties profileProperties;
    private final SkillService skillService;
    private final ProjectService projectService;
    private final ContactService contactService;

    public HomeController(ProfileProperties profileProperties, SkillService skillService, ProjectService projectService, ContactService contactService) {
        this.profileProperties = profileProperties;
        this.skillService = skillService;
        this.projectService = projectService;
        this.contactService = contactService;
    }
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("name", profileProperties.fullName());
        model.addAttribute("title", profileProperties.title());
        model.addAttribute("about", profileProperties.about());
        model.addAttribute("email", profileProperties.email());
        model.addAttribute("githubUrl", profileProperties.githubUrl());
        model.addAttribute("skills", skillService.findAllNames());
        model.addAttribute("projects", projectService.findAll());

        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "index";
    }
    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute("contactForm") ContactForm form, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            // Giữ lại lỗi và dữ liệu đã nhập qua lần redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.contactForm", bindingResult);
            redirectAttributes.addFlashAttribute("contactForm", form);
            return "redirect:/#contact";
        }

        contactService.save(form);
        redirectAttributes.addFlashAttribute("success", true);
        return "redirect:/#contact";
    }
    }

