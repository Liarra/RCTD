package storedentities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 11:02:22
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Type {

    private Long id;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String name;

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Collection<Donate> donates;

    @OneToMany(mappedBy = "type")
    public Collection<Donate> getDonates() {
        return donates;
    }

    public void setDonates(Collection<Donate> donates) {
        this.donates = donates;
    }
}
