package zw.co.sms.gsm.domain;

import javax.persistence.Entity;

@Entity
public class Faculty extends BaseEntityId {

    private String name;
    private  String facultyCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(String facultyCode) {
        this.facultyCode = facultyCode;
    }
}
