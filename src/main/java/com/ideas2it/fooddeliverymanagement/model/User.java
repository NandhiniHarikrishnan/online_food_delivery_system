package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A User is a person who can log in to the system.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    private List<Address> addresses;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Role.class, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
    joinColumns = { @JoinColumn(name = "user_id")},
    inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();
}
