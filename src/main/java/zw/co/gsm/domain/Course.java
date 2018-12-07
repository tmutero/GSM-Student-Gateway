package zw.co.gsm.domain;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 * Created by zinzombe on Sep
 */

@Entity
public class Course extends BaseEntityId {

    private String name;
    private String courseCode;
    private Degree degree;

    @Enumerated
    private Level level;


    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }



    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    @ManyToOne
    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
