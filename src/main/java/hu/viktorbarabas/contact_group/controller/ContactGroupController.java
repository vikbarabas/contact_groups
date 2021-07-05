package hu.viktorbarabas.contact_group.controller;

import hu.viktorbarabas.contact_group.entities.Contact;
import hu.viktorbarabas.contact_group.entities.ContactGroup;
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

    @GetMapping(value = "/")
    public String index(Model model) {
        return service.getIndex(model);
    }

    @GetMapping(value = "/newGroup")
    public String createGroup(Model model) {
        return service.createGroup(model);
    }
    @GetMapping(value = "/newContact")
    public String createContact(Model model) {
        return service.createContact(model);
    }

    @PostMapping(value = "/addGroup")
    public String addGroup(
            @ModelAttribute ContactGroup contactGroup,
            Model model) {
        return service.addGroup(contactGroup, model);
    }
    @PostMapping(value = "/addContact")
    public String addContact(
            @ModelAttribute Contact contact,
            Model model) {
        return service.addContact(contact, model);
    }

    @PostMapping(value = "/selectGroup")
    public String selectGroup(
            @RequestParam("groupName") String groupName,
            @RequestParam("rowCount") int rowCount,
            Model model) {
        return service.selectGroup(groupName, rowCount, model);
    }
    @PostMapping(value = "/selectContact")
    public String selectContact(
            @RequestParam("contactName") String contactName,
            @RequestParam("rowCount") int rowCount,
            Model model) {
        return service.selectContact(contactName, rowCount, model);
    }

    @GetMapping(value = "/editGroup")
    public String editGroup(Model model) {
        return service.updateGroup(model);
    }
    @GetMapping(value = "/editContact")
    public String editContact(Model model) {
        return service.updateContact(model);
    }
    @GetMapping(value = "/deleteGroup")
    public String deleteGroup(Model model) {
        return service.deleteGroup(model);
    }
    @GetMapping(value = "/deleteContact")
    public String deleteContact(Model model) {
        return service.deleteContact(model);
    }

}
