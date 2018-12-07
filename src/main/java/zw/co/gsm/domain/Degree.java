package zw.co.gsm.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by zinzombe on Oct
 */
@Entity
public class Degree extends BaseEntityId {

    private String name;
    private Faculty faculty;

    private String degreeCode;

    public String getDegreeCode() {
        return degreeCode;
    }

    public void setDegreeCode(String degreeCode) {
        this.degreeCode = degreeCode;
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
