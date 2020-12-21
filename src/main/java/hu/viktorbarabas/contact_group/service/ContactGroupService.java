package hu.viktorbarabas.contact_group.service;

import hu.viktorbarabas.contact_group.Contacts;
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

    private int nextId;

    @Autowired
    public void setContactGroupsRepository(ContactGroupsRepository contactGroupsRepository) {
        this.contactGroupsRepository = contactGroupsRepository;
    }

    @Autowired
    public void setContactsRepository(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    public String getIndex(Model model) {

        uploadContactList(model);

        return "index";

    }

    public String getContact(Model model) {
        model.addAttribute("contacts", new Contacts());
        return "new_group";
    }

    public String addContact(Contacts contacts, Model model) {
        nextId++;
        contacts.setId(nextId);
        contactsRepository.save(contacts);
        uploadContactList(model);
        return "index";
    }

    private void uploadContactList(Model model) {
        model.addAttribute("btnName", "New Contact");
        List<Contacts> contacts = new LinkedList<>();
        if (contactsRepository.findAll().size() > 0){
            contacts = contactsRepository.findAll();
        } else {
            contacts.add(new Contacts("empty", "empty", "empty"));
        }
        model.addAttribute("contacts", contacts);

    }
}
