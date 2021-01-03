package hu.viktorbarabas.contact_group.service;

import hu.viktorbarabas.contact_group.entities.ContactGroups;
import hu.viktorbarabas.contact_group.entities.Contacts;
import hu.viktorbarabas.contact_group.repository.ContactGroupsRepository;
import hu.viktorbarabas.contact_group.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.LinkedList;
import java.util.List;

@Service
public class ContactGroupService {

    private ContactGroupsRepository contactGroupsRepository;
    private ContactsRepository contactsRepository;

    private ContactGroups savedGroup;

    @Autowired
    public void setContactGroupsRepository(ContactGroupsRepository contactGroupsRepository) {
        this.contactGroupsRepository = contactGroupsRepository;
    }

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public String getIndex(Model model) {
        initialize(model);
        return "index";

    }

    public String createGroupToNew(Model model) {
        model.addAttribute("contactGroups", new ContactGroups());
        return "newGroup";
    }

    public String createContactToNew(Model model) {
        model.addAttribute("contacts", new Contacts());
        return "newContact";
    }

    // test
    public String addGroup(ContactGroups contactGroups, Model model) {
        contactGroupsRepository.save(contactGroups);
        initialize(model);
        return "index";
    }

    public String addContact(Contacts contacts, Model model) {
        if (savedGroup != null) {
            contacts.setContactGroups(savedGroup);
            contactsRepository.save(contacts);
        }
        initialize(model);
        return "index";
    }

    public String saveGroupObjectBySelectedRow(String groupName, Model model) {
        savedGroup = new ContactGroups();
        savedGroup = contactGroupsRepository.findById(groupName);
        initialize(model);
        model.addAttribute("status", savedGroup.getId());
        return "index";
    }

    private void initialize(Model model) {

        model.addAttribute("btnNewContactGroup", "New Contact Group");
        List<ContactGroups> contactGroups = new LinkedList<>();
        if (contactGroupsRepository.findAll().size() > 0) {
            model.addAttribute("btnNewContact", "New Contact");
            contactGroups = contactGroupsRepository.findAll();
        }
        if (contactGroups.isEmpty()) {
            model.addAttribute("contactGroups", new ContactGroups("empty"));
            model.addAttribute("btnNewContact", "-");
            model.addAttribute("contacts" ,new Contacts("-", "-", "-", null));
        } else {
            List<Contacts> contacts = new LinkedList<>();
            long groupId;
            if (savedGroup == null) {
                groupId = contactGroups.get(0).getId();
            } else {
                groupId = savedGroup.getId();
            }
            if (!contactsRepository.findAllByContactGroupsId(groupId).isEmpty()) {
                contacts = contactsRepository.findAllByContactGroupsId(groupId);
            } else {
                contacts.add(new Contacts("empty", "empty", "empty", null));
            }
            model.addAttribute("contactGroups", contactGroups);
            model.addAttribute("contacts", contacts);
        }
        model.addAttribute("status", "not");

    }

    public String sendGroupObjectBySelectedRow(String groupName, Model model) {
        model.addAttribute("contactGroups", contactGroupsRepository.findById(groupName));
        return "newGroup";
    }
}
