package hu.viktorbarabas.contact_group;

import javax.persistence.*;
import java.util.List;

@Entity
public class Contacts {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String phoneNumber;
    private String eMail;

    public Contacts() {
    }

    public Contacts(String name, String phoneNumber, String eMail) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.eMail = eMail;
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

}
