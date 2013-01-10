package storedentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Basic;
import javax.persistence.EntityListeners;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 11:22:09
 * To change this template use File | Settings | File Templates.
 */
@EntityListeners(EntityListener.class)
@Entity
public class Ad {
    private String id;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String account;

    @Basic
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private String link;

    @Basic
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String picURL;

    @Basic
    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }
}
