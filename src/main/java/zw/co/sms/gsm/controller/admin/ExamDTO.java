package zw.co.sms.gsm.controller.admin;

import zw.co.sms.gsm.domain.Course;

import java.util.List;

public class ExamDTO {

    private String name;
    private String id;
    private String courseName;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
