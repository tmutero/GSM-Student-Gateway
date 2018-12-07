package zw.co.gsm.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Registration extends BaseEntityId{

    private Course course;
    private Student student;
    private String examMark;
    

    public String getExamMark() {
        return examMark;
    }

    public void setExamMark(String examMark) {
        this.examMark = examMark;
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "course=" + course +
                ", student=" + student +
                ", examMark='" + examMark + '\'' +
                '}';
    }
}
