package zw.co.gsm.domain.sms;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ezinzombe on 4/12/18.
 */

@MappedSuperclass
public abstract class BaseId implements Serializable {

    private Long ids;
    private Date dateCreated;
    private Date dateModified;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIds() {
        return ids;
    }

    public void setIds(Long ids) {
        this.ids = ids;
    }



    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    @PrePersist
    public void addPrePersistAuditInfo() {
        setCreatedDateIfNotSet();
    }

    private void setCreatedDateIfNotSet() {

        if (getDateCreated() == null) {

            setDateCreated(new Date());
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseId baseId = (BaseId) o;

        return ids != null ? ids.equals(baseId.ids) : baseId.ids == null;
    }

    @Override
    public int hashCode() {
        return ids != null ? ids.hashCode() : 0;
    }
}
