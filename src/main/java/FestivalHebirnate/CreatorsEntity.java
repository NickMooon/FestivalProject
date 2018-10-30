package FestivalHebirnate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "creators", schema = "festivaldata")
public class CreatorsEntity {
    private int iDcreate;
    private String name;
    private int quantity;
    private String genre;
    private String festival;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "creators_has_eventinfo",
            joinColumns = { @JoinColumn(name = "Creators_IDcreate") },
            inverseJoinColumns = { @JoinColumn(name = "EventINFO_ID") }
    )
    Set<EventinfoEntity> event2 = new HashSet<EventinfoEntity>();

    public void addFest(EventinfoEntity fest) {
        if (fest == null)
            throw new NullPointerException();
        event2.add(fest);
    }

    // getters & setters

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDcreate", nullable = false)
    public int getiDcreate() {
        return iDcreate;
    }

    public void setiDcreate(int iDcreate) {
        this.iDcreate = iDcreate;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "Genre", nullable = false, length = 45)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Basic
    @Column(name = "Festival", nullable = false, length = 255)
    public String getFestival() {
        return festival;
    }

    public void setFestival(String festival) {
        this.festival = festival;
    }

    public Set<EventinfoEntity> getEvent(){
        return event2;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreatorsEntity that = (CreatorsEntity) o;

        if (iDcreate != that.iDcreate) return false;
        if (quantity != that.quantity) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;
        if (festival != null ? !festival.equals(that.festival) : that.festival != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iDcreate;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (festival != null ? festival.hashCode() : 0);
        return result;
    }
}
