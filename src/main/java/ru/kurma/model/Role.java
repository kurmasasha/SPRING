package ru.kurma.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role", schema = "test")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role")
    private String role;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, optional = true)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "role_id")
            , inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return getRole();
    }
}
