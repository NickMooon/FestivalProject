package FestivalHebirnate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "eventinfo", schema = "festivaldata")
public class EventinfoEntity {
    private int id;
    private Timestamp date;
    private String place;
    private int maxUsers;
    private String eventIName;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "place", nullable = false, length = 255)
    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Basic
    @Column(name = "MaxUsers", nullable = false)
    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    @Basic
    @Column(name = "EventIName", nullable = false, length = 250)
    public String getEventIName() {
        return eventIName;
    }

    public void setEventIName(String eventIName) {
        this.eventIName = eventIName;
    }

    @ManyToMany(mappedBy = "event2")
    private Set<CreatorsEntity> creators = new HashSet<CreatorsEntity>();
    public Set<CreatorsEntity> getCreators(){
        return creators;
    }

    @ManyToMany(mappedBy = "event1")
    private Set<UsersEntity> user = new HashSet<>();
    public Set<UsersEntity> getUser(){
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventinfoEntity that = (EventinfoEntity) o;

        if (id != that.id) return false;
        if (maxUsers != that.maxUsers) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (eventIName != null ? !eventIName.equals(that.eventIName) : that.eventIName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (place != null ? place.hashCode() : 0);
        result = 31 * result + maxUsers;
        result = 31 * result + (eventIName != null ? eventIName.hashCode() : 0);
        return result;
    }
}
