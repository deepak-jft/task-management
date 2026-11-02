package org.jft.controller;

import org.jft.model.Contact;
import org.jft.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public String showContactForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping
    public String submitContact(Contact contact, Model model) {
        contactService.saveContact(contact);
        model.addAttribute("success", true);
        model.addAttribute("contact", new Contact());
        return "contact";
    }
}
