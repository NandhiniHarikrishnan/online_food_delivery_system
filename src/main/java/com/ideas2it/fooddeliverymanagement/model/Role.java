package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * A Role can be assigned to many Users, and a User can have many Roles
 * It contains id, name, code, user's.
 *
 * @author - dilip.n
 * @version - 1.0
 * @since - 2022-12-16
 */
@Entity
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class Role extends BaseModel {

    @NotEmpty
    private String name;

    private String code;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


}
