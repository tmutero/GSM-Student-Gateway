package zw.co.gsm.domain.sms;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by ezinzombe on 7/14/17.
 */
@Entity
@XmlRootElement
public class ServerResponse extends BaseId implements Comparable<ServerResponse> {



    private List<Message> messages;
    private String description;
    private String requestMethod;
    private String isSuccessful;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(String isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    @OneToMany(mappedBy = "serverResponse")
    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


    @Override
    public String toString() {
        return "ServerResponse{" +
                "messages=" + messages +
                ", description='" + description + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", isSuccessful='" + isSuccessful + '\'' +
                '}';
    }

    @Override
    public int compareTo(ServerResponse o) {
        return 0;
    }
}
