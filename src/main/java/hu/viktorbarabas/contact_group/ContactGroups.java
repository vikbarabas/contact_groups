package hu.viktorbarabas.contact_group;

import javax.persistence.*;
import java.util.List;

@Entity
public class ContactGroups {

    @Id
    @GeneratedValue
    private long id;

    private String groupName;

    public ContactGroups() {
    }

    public ContactGroups(String groupName) {
        this.groupName = groupName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
