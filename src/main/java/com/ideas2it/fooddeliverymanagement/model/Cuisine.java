package com.ideas2it.fooddeliverymanagement.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Cuisine extends BaseModel {

    @NotNull
    @Column(columnDefinition="varchar(20) unique")
    private String code;

    @NotNull
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cuisine_id", referencedColumnName = "id")
    private List<Restaurant> restaurants;

    public Cuisine() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

