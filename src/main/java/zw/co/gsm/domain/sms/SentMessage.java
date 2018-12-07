package zw.co.gsm.domain.sms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author tafadzwa
 * @author edmond
 */
@Entity
@Table(name = "sent_message")
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement
public class SentMessage extends BaseId implements Comparable<SentMessage> {


    private String to;
    private String id;
    private String message;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SentMessage{" +
                "to='" + to + '\'' +
                ", id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public int compareTo(SentMessage o) {
        return 0;
    }
}
