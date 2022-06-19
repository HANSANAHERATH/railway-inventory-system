package com.railway.railwayservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User details.
 */
@Entity
@Data
@Table(name = "user_details")
public class UserDetails implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public UserDetails(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserDetails() {
    }

}