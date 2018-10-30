package FestivalHebirnate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "festivaldata")
public class UsersEntity {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String email;



    public void addFest(EventinfoEntity fest) {
        if (fest == null)
            throw new NullPointerException();
        event1.add(fest);
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FirstName", nullable = false, length = 45)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LastName", nullable = false, length = 60)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "AGE", nullable = false)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "EMAIL", nullable = true, length = 120)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "users_has_eventinfo",
            joinColumns = { @JoinColumn(name = "Users_ID") },
            inverseJoinColumns = { @JoinColumn(name = "EventINFO_ID") }
    )
    private Set<EventinfoEntity> event1 = new HashSet<>();
    public void setEvent(Set<EventinfoEntity> event1){
        this.event1=event1;
    }
    public Set<EventinfoEntity> getEvent() {
        return event1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (age != that.age) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
