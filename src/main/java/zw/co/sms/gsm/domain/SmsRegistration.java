package zw.co.sms.gsm.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

/**
 * Created by zinzombe on Oct
 */
@Entity(name = "sms_registration")
public class SmsRegistration extends BaseEntityId {

    private String name;
    private User user;



    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
