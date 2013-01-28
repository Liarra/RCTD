package storedentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Basic;

/**
 * Created by IntelliJ IDEA.
 * User: Buchina
 * Date: 10.01.2013
 * Time: 11:22:09
 * To change this template use File | Settings | File Templates.
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
