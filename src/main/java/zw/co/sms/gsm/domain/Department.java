package zw.co.sms.gsm.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by zinzombe on Oct
 */
@Entity
public class Department extends BaseEntityId {

    private String name;
    private Faculty faculty;
    private User user;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
