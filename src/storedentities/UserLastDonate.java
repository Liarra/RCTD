package storedentities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 11:23:01
 */
@Entity
public class UserLastDonate {
    private Long id;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String user_id;

    @Basic
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    private Date donateDate;

    @Basic
    public Date getDonateDate() {
        return donateDate;
    }

    public void setDonateDate(Date donateDate) {
        this.donateDate = donateDate;
    }

    private Donate donate;

    @ManyToOne(optional = false)
    public Donate getDonate() {
        return donate;
    }

    public void setDonate(Donate donate) {
        this.donate = donate;
    }
}
