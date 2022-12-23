/*
 * Copyright 2022 Ideas2IT Technologies. All rights reserved.
 * IDEAS2IT PROPRIETARY/CONFIDENTIAL.
 */
package com.ideas2it.fooddeliverymanagement.model;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * It is a role entity that contains id, name, code, user's.
 * A Role can be assigned to many Users, and a User can have many Roles
 * Role code always unique.
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

    @NotNull
    private String name;

    private String code;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


}
