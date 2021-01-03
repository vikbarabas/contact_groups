package hu.viktorbarabas.contact_group.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContactGroups {

    /**
     * Nem volt elég az
     * "@Id"
     * "@GeneratedValue"
     * mert itt a "ContactGroups"nál 2 lett az első id...?
     * Emiatt kellett ez a sok szar
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private String name;

    public ContactGroups() {
    }

    public ContactGroups(String name) {
        this.name = name;
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

    public void setName(String groupName) {
        this.name = groupName;
    }

}
