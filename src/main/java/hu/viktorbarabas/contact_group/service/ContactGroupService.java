package hu.viktorbarabas.contact_group.service;

import hu.viktorbarabas.contact_group.entities.ContactGroup;
import hu.viktorbarabas.contact_group.entities.Contact;
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

    private ContactGroup existGroups;
    private Contact existContact;

    private int selectRow;
    private int selectedRow;

    private boolean isEditing;

    @Autowired
    public void setContactGroupsRepository(ContactGroupsRepository contactGroupsRepository) {
        this.contactGroupsRepository = contactGroupsRepository;
    }

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public String getIndex(Model model) {
        return initializing(model);
    }

    private String initializing(Model model) {

        model.addAttribute("btnNewContactGroup", "New Contact Group");
        model.addAttribute("btnNewContact", "New Contact");

        List<ContactGroup> group = new LinkedList<>(contactGroupsRepository.findAll());
        if (group.isEmpty()) {

            model.addAttribute("contactGroups", new ContactGroup("empty"));
            model.addAttribute("btnNewContact", "-");
            model.addAttribute("contacts", new Contact("-", "-", "-", null));

        } else {

            long groupId;
            if (existGroups == null) {

                groupId = group.get(0).getId();

            } else {

                groupId = existGroups.getId();
                model.addAttribute("panelHeadLabel", existGroups.getName());

            }

            List<Contact> contacts = new LinkedList<>(contactsRepository.findAllByContactGroupsId(groupId));
            if (contacts.isEmpty()) {

                contacts.add(new Contact("empty", "empty", "empty", null));

            }
            model.addAttribute("contactGroups", group);
            model.addAttribute("contacts", contacts);

        }

        model.addAttribute("currSelectedGroup", selectRow);
        selectRow = -1;

        return "index";
    }

    public String createGroup(Model model) {

        model.addAttribute("contactGroups", new ContactGroup());

        return "newGroup";
    }

    public String createContact(Model model) {

        model.addAttribute("contacts", new Contact());

        return "newContact";
    }

    public String addGroup(ContactGroup contactGroup, Model model) {

        if (!isEditing) {

            contactGroupsRepository.save(contactGroup);

        } else {

            isEditing = false;
            existGroups.setName(contactGroup.getName());
            contactGroupsRepository.save(existGroups);

        }

        return initializing(model);
    }

    public String addContact(Contact contact, Model model) {

        if (!isEditing) {

            contact.setContactGroups(existGroups);
            contactsRepository.save(contact);

        } else {

            isEditing = false;
            existContact.setName(contact.getName());
            existContact.setEmail(contact.getEmail());
            existContact.setPhoneNumber(contact.getPhoneNumber());
            contactsRepository.save(existContact);

        }

        return initializing(model);
    }

    public String selectGroup(String groupName, int rowCount, Model model) {

        if (!groupName.equals("empty")) {

            selectRow = rowCount; // save row count
            selectedRow = selectRow;

            if (existGroups == null)
                existGroups = new ContactGroup();
            existGroups = contactGroupsRepository.findById(groupName);
        }

        return initializing(model);
    }

    public String selectContact(String contactName, int rowCount, Model model) {

        if (!contactName.equals("empty")) {

            selectRow = rowCount; // save row count
            selectedRow = selectRow;

            if (existContact == null)
                existContact = new Contact();
            existContact = contactsRepository.findByName(contactName);
        }

        return initializing(model);
    }

    public String updateGroup(Model model) {

        isEditing = true;
        model.addAttribute("contactGroups", existGroups);

        return "newGroup";
    }

    public String updateContact(Model model) {

        isEditing = true;
        model.addAttribute("contacts", existContact);

        return "newContact";
    }

    public String deleteGroup(Model model) {

        if (selectedRow >= 0) {
            contactGroupsRepository.delete(existGroups);
        }

        return initializing(model);
    }

    public String deleteContact(Model model) {

        if (selectedRow >= 0) {
            contactsRepository.delete(existContact);
        }

        return initializing(model);
    }
}
