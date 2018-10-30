import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "public")
    public class User {
    private int ID;
    private String NAME;
    private String PASSWORD;
    private String EMAIL;
    private String Nikname;
    private String SecName;
    private int AGE;

}
