package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@Getter
@Setter
@Where(clause = "is_deleted = false")
public class Role extends BaseModel {
    @NotNull
    private String name;

    @NotNull
    @Column(columnDefinition="varchar(20) unique")
    private String code;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


}
