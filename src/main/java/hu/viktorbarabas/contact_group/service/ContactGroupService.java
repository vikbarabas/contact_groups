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
    private int rowCount;
    private boolean isEdit;

    @Autowired
    public void setContactGroupsRepository(ContactGroupsRepository contactGroupsRepository) {
        this.contactGroupsRepository = contactGroupsRepository;
    }

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public String getIndex(Model model) {
        return initialize(model);
    }

    public String createGroupToNew(Model model) {
        model.addAttribute("contactGroups", new ContactGroups());
        return "newGroup";
    }

    public String createContactToNew(Model model) {
        if (rowCount == 0) {
            rowCount = -1;
            return initialize(model);
        }
        model.addAttribute("contacts", new Contacts());
        return "newContact";
    }

    public String addGroup(ContactGroups contactGroups, Model model) {
        if (!isEdit) {
            contactGroupsRepository.save(contactGroups);
        } else {
            isEdit = false;
            savedGroup.setName(contactGroups.getName());
            contactGroupsRepository.save(savedGroup);
        }
        return initialize(model);
    }

    public String addContact(Contacts contacts, Model model) {
        if (savedGroup != null) {
            contacts.setContactGroups(savedGroup);
            contactsRepository.save(contacts);
        }
        return initialize(model);
    }

    public String updateSelectedGroup(Model model) {
        if (rowCount == 0) {
            rowCount = -1;
            initialize(model);
        }
        isEdit = true;
        model.addAttribute("contactGroups", savedGroup);
        return "newGroup";
    }

    public String deleteSelectedGroup(Model model) {
        if (rowCount != 0) {
            contactGroupsRepository.delete(savedGroup);
        } else {
            rowCount = -1;
        }

        return initialize(model);
    }

    public String saveGroupObjectBySelectedRow(String groupName, int rowCountIn, Model model) {
        if (savedGroup == null) {
            rowCount = rowCountIn;
            savedGroup = new ContactGroups();
            savedGroup = contactGroupsRepository.findById(groupName);
        }
        if (!savedGroup.getName().equals("") && !savedGroup.getName().equals(groupName)) {
            rowCount = rowCountIn;
            savedGroup = contactGroupsRepository.findById(groupName);
        }
        return initialize(model);
    }

    private String initialize(Model model) {

        model.addAttribute("btnNewContactGroup", "New Contact Group");
        List<ContactGroups> contactGroups = new LinkedList<>();
        if (contactGroupsRepository.findAll().size() > 0) {
            model.addAttribute("btnNewContact", "New Contact");
            contactGroups = contactGroupsRepository.findAll();
        }
        if (contactGroups.isEmpty()) {
            model.addAttribute("contactGroups", new ContactGroups("empty"));
            model.addAttribute("btnNewContact", "-");
            model.addAttribute("contacts", new Contacts("-", "-", "-", null));
        } else {
            List<Contacts> contacts = new LinkedList<>();
            long groupId;
            if (savedGroup == null) {
                groupId = contactGroups.get(0).getId();
            } else {
                groupId = savedGroup.getId();
                model.addAttribute("panelHeadLabel", savedGroup.getName());
            }
            if (!contactsRepository.findAllByContactGroupsId(groupId).isEmpty()) {
                contacts = contactsRepository.findAllByContactGroupsId(groupId);
            } else {
                contacts.add(new Contacts("empty", "empty", "empty", null));
            }
            model.addAttribute("contactGroups", contactGroups);
            model.addAttribute("contacts", contacts);
        }
        model.addAttribute("noSelectedRowException", rowCount);
        rowCount = 0;

        return "index";

    }

}
