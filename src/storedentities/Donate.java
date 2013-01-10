package storedentities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 10:47:57
 * To change this template use File | Settings | File Templates.
 */
@EntityListeners(EntityListener.class)
@Entity
public class Donate {
    private Long id;
    private String name;
    private String picURL;
    private String description;
    private String accountNumber;

    @Id
    @GeneratedValue
    @Column
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getPicURL() {
        return picURL;
    }

    public void setPicURL(String picURL) {
        this.picURL = picURL;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    private Type type;

    @ManyToOne(optional = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    private Collection<UserLastDonate> lastDonates;

    @OneToMany(mappedBy = "donate")
    public Collection<UserLastDonate> getLastDonates() {
        return lastDonates;
    }

    public void setLastDonates(Collection<UserLastDonate> lastDonates) {
        this.lastDonates = lastDonates;
    }
}
