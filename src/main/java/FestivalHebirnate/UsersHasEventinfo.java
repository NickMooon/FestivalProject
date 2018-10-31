package FestivalHebirnate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_has_eventinfo", schema = "festivaldata", catalog = "")
@IdClass(UsersHasEventinfoPK.class)
public class UsersHasEventinfo {
    private int usersId;
    private int eventId;

    @Id
    @Column(name = "Users_ID", nullable = false)
    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Id
    @Column(name = "Event_ID", nullable = false)
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersHasEventinfo that = (UsersHasEventinfo) o;
        return usersId == that.usersId &&
                eventId == that.eventId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, eventId);
    }
}
