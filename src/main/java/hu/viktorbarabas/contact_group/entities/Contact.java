package hu.viktorbarabas.contact_group.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String phoneNumber;
    private String email;
    @ManyToOne
    private ContactGroup contactGroup;

    public Contact() {
    }

    public Contact(String name, String phoneNumber, String email, ContactGroup contactGroup) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactGroup = contactGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String eMail) {
        this.email = eMail;
    }

    public ContactGroup getContactGroups() {
        return contactGroup;
    }

    public void setContactGroups(ContactGroup contactGroup) {
        this.contactGroup = contactGroup;
    }
}
