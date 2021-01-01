package hu.viktorbarabas.contact_group;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;
    private String phoneNumber;
    private String eMail;
    @ManyToOne
    private ContactGroups contactGroups;

    public Contacts() {
    }

    public Contacts(String name, String phoneNumber, String eMail, ContactGroups contactGroups) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
        this.contactGroups = contactGroups;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public ContactGroups getContactGroups() {
        return contactGroups;
    }

    public void setContactGroups(ContactGroups contactGroups) {
        this.contactGroups = contactGroups;
    }
}
