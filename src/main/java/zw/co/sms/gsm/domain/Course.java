package zw.co.sms.gsm.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by zinzombe on Sep
 */

@Entity
public class Course extends BaseEntityId {

    private String name;
    private String courseCode;
    private Department department;
    private User user;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    @ManyToOne
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
