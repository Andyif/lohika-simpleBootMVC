import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Skill {

    @Id
    @GeneratedValue
    private long    id;
    private String  label;
    private String  description;

    public Skill() {
    }

    public Skill(long id, String label, String description) {
        this.id             = id;
        this.label          = label;
        this.description    = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
