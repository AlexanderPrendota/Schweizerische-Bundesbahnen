package com.schweizerischebundesbahnen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by aleksandrprendota on 22.03.17.
 */

@Data
@Entity
@Table(name = "ROLES")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public long id;

    @Column(name = "role")
    public String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    public Set<User> userSet;

    @Override
    public String getAuthority() {
        return getRole();
    }
    public Role(String name) {
        role = name;
    }
    protected Role(){}
}
