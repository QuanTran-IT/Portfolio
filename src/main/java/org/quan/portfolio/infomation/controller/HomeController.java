package org.quan.portfolio.infomation.controller;


import jakarta.validation.Valid;
import org.quan.portfolio.infomation.dto.ContactForm;
import org.quan.portfolio.infomation.dto.HomePageDto;

import org.quan.portfolio.infomation.service.ContactService;
import org.quan.portfolio.infomation.service.HomePageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller

public class HomeController {
    private final HomePageService homePageService;
    private final ContactService contactService;

    public HomeController(HomePageService homePageService, ContactService contactService) {
        this.homePageService = homePageService;
        this.contactService = contactService;
    }
    @GetMapping("/")
    public String home(Model model) {
        HomePageDto page = homePageService.getHomePage();
        model.addAttribute("page", page);

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

