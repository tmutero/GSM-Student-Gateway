package zw.co.gsm.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "regNumber"))
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String regNumber;
    private String password;
    private Boolean approved = Boolean.TRUE;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
    @ManyToOne
    private Degree degree;

    @OneToMany(mappedBy = "student")
    private List<StudentAccount> studentAccounts;

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Enumerated
    private Level level;

    @Enumerated
    private Gender gender;

    public List<StudentAccount> getStudentAccounts() {
        return studentAccounts;
    }

    public void setStudentAccounts(List<StudentAccount> studentAccounts) {
        this.studentAccounts = studentAccounts;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }



    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transient
    public String getFullName() {
        return lastName + " " + firstName;
    }

    @Transient
    public String getAge() {
        if (getIntegerValueOfAge() < 1) {
            return " less 1 yr ";
        } else {
            return getIntegerValueOfAge() + " yrs";
        }
    }

    @Transient
    public int getIntegerValueOfAge() {
        if (getDateOfBirth() == null) {
            return 0;
        }

        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(getDateOfBirth());

        Calendar todayCalendar = Calendar.getInstance();

        int age = todayCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        if (todayCalendar.get(Calendar.MONTH) < birthCalendar.get(Calendar.MONTH)) {
            age--;
        } else if (todayCalendar.get(Calendar.MONTH) == birthCalendar.get(Calendar.MONTH)
                && todayCalendar.get(Calendar.DAY_OF_MONTH) < birthCalendar.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }

        return age;
    }

    public int getColumnCount() {

        return getClass().getDeclaredFields().length;
    }

}