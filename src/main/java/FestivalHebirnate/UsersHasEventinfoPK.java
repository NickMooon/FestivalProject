package FestivalHebirnate;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UsersHasEventinfoPK implements Serializable {
    private int usersId;
    private int eventId;

    @Column(name = "Users_ID", nullable = false)
    @Id
    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    @Column(name = "Event_ID", nullable = false)
    @Id
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
        UsersHasEventinfoPK that = (UsersHasEventinfoPK) o;
        return usersId == that.usersId &&
                eventId == that.eventId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersId, eventId);
    }
}
