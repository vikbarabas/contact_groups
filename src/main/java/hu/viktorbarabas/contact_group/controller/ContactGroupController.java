package hu.viktorbarabas.contact_group.controller;

import hu.viktorbarabas.contact_group.entities.ContactGroups;
import hu.viktorbarabas.contact_group.entities.Contacts;
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

    @RequestMapping(value = "/newGroup", method = RequestMethod.GET)
    public String newGroupForm(Model model) {
        return service.createGroupToNew(model);
    }

    @RequestMapping(value = "/sendGroupName", method = RequestMethod.POST)
    public String getGroupNameBySelected(@RequestParam("groupName") String groupName, Model model) {
        return service.saveGroupObjectBySelectedRow(groupName, model);
    }

    @RequestMapping(value = "/editGroup", method = RequestMethod.POST)
    public String sendGroupBySelected(@RequestParam("groupName") String groupName, Model model) {
        return service.sendGroupObjectBySelectedRow(groupName, model);
    }

    /*@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
    public String sendGroupBySelected(@RequestParam("groupName") String groupName, Model model) {
        return service.sendGroupObjectBySelectedRow(groupName, model);
    }*/

    @RequestMapping(value = "/newContact", method = RequestMethod.GET)
    public String newContactForm(Model model) {
        return service.createContactToNew(model);
    }

    @RequestMapping(value = "/addGroup", method = RequestMethod.POST)
    public String saveNewGroup(@ModelAttribute ContactGroups contactGroups, Model model) {
        return service.addGroup(contactGroups, model);
    }

    @RequestMapping(value = "/addContact", method = RequestMethod.POST)
    public String saveNewContact(@ModelAttribute Contacts contacts, Model model) {
        return service.addContact(contacts, model);
    }

}
