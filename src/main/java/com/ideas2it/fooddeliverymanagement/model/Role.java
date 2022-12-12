package com.ideas2it.fooddeliverymanagement.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Role can be assigned to many Users, and a User can have many Roles
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType. IDENTITY)
    private int id;

    private String name;

    private String code;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles", cascade=CascadeType.ALL)
    private List<User> users = new ArrayList<>();
}
