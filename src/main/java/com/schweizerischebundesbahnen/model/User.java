package com.schweizerischebundesbahnen.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by aleksandrprendota on 22.03.17.
 */


@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "ID", nullable = false)
    public long id;

    @Column(name = "EMAIL", unique = true)
    @Getter
    @Setter
    public String email;

    @Column(name = "FIRSTNAME")
    @Getter
    @Setter
    public String firstname;

    @Column(name = "LASTNAME")
    @Getter
    @Setter
    public String lastname;

    @Column(name = "PASSWORD")
    @Getter
    @Setter
    public String password;

    @Temporal(TemporalType.DATE)
    @Getter
    @Setter
    @Column(name = "BIRTHDAY")
    public Date birthday;

    @Column(name = "IMAGE_PATH")
    @Getter
    @Setter
    public String imagepath;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Set<Ticket> tickets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
