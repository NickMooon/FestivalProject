package FestivalHebirnate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Event {
    private int eventId;
    private Timestamp date;
    private String place;
    private int maxUsers;
    private String eventIName;
    private int price;

    @Id
    @Column(name = "EventID", nullable = false)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId &&
                maxUsers == event.maxUsers &&
                price == event.price &&
                Objects.equals(date, event.date) &&
                Objects.equals(place, event.place) &&
                Objects.equals(eventIName, event.eventIName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, date, place, maxUsers, eventIName, price);
    }
}
