package com.monolithmind.task.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
public class User extends BaseEntity{
    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Purchase> purchases = new ArrayList<>();
}
