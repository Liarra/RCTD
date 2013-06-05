package storedentities;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 11:02:22
 */
@Entity
public class Type {

    private Long id;
    private String name;
    private Collection<Donate> donates;

    protected Type() {

    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "type")
    public Collection<Donate> getDonates() {
        return donates;
    }

    public void setDonates(Collection<Donate> donates) {
        this.donates = donates;
    }
}
