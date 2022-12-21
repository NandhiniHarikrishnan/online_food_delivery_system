package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * A User is a person who can create a filed like name, email, addresses, roles, orders,.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-10
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class User extends BaseModel{

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    private String status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id" ,referencedColumnName = "id")
    private List<Address> addresses;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
    joinColumns = { @JoinColumn(name = "user_id")},
    inverseJoinColumns = { @JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "customer_Id", referencedColumnName ="id")
    private List<Order> orders;

}
