package FestivalHebirnate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Autoris {
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
        Autoris autoris = (Autoris) o;
        return idAutoris == autoris.idAutoris &&
                Objects.equals(userName, autoris.userName) &&
                Objects.equals(password, autoris.password) &&
                Objects.equals(permission, autoris.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAutoris, userName, password, permission);
    }
}
