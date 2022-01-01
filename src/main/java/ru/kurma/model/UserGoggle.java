package ru.kurma.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "usergoogle")
public class UserGoggle {

    @Id
    private String id;
    private String name;
    private String userpic;
    private String email;
    private String genger;
    private String locale;
    private LocalDateTime lastVisit;

    public UserGoggle() {
    }

    public UserGoggle(String id, String name, String userpic, String email, String genger, String locale, LocalDateTime lastVisit) {
        this.id = id;
        this.name = name;
        this.userpic = userpic;
        this.email = email;
        this.genger = genger;
        this.locale = locale;
        this.lastVisit = lastVisit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenger() {
        return genger;
    }

    public void setGenger(String genger) {
        this.genger = genger;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Override
    public String toString() {
        return "UserGoggle{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", userpic='" + userpic + '\'' +
                ", email='" + email + '\'' +
                ", genger='" + genger + '\'' +
                ", locale='" + locale + '\'' +
                ", lastVisit=" + lastVisit +
                '}';
    }
}
