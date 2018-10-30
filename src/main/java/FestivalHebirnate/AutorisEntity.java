package FestivalHebirnate;

import javax.persistence.*;

@Entity
@Table(name = "autoris", schema = "festivaldata")
public class AutorisEntity {
    private int idAutoris;
    private String userName;
    private String password;
    private String permission;

    @Id
    @Column(name = "idAutoris", nullable = false)
    public int getIdAutoris() {
        return idAutoris;
    }

    public void setIdAutoris(int idAutoris) {
        this.idAutoris = idAutoris;
    }

    @Basic
    @Column(name = "UserName", nullable = false, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Permission", nullable = false, length = 45)
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutorisEntity that = (AutorisEntity) o;

        if (idAutoris != that.idAutoris) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAutoris;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }
}
