package hu.viktorbarabas.contact_group.controller;

import hu.viktorbarabas.contact_group.Contacts;
import hu.viktorbarabas.contact_group.service.ContactGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContactGroupController {

    private ContactGroupService service;

    @Autowired
    public void setService(ContactGroupService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        return service.getIndex(model);
    }

    @RequestMapping(value = "/new_group", method = RequestMethod.GET)
    public String newContactForm(Model model) {
        return service.getContact(model);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveNewContact(@ModelAttribute Contacts contacts, Model model) {
        return service.addContact(contacts, model);
    }

}
