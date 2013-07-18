package datasource.storedentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Basic;

/**
 * Created with IntelliJ IDEA.
 * UserClickData: Buchina
 * Date: 10.01.2013
 * Time: 11:22:09
 */
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

    private String script;

    @Basic
    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    private String HTML;

    @Basic
    public String getHTML() {
        return HTML;
    }

    public void setHTML(String HTML) {
        this.HTML = HTML;
    }
}
