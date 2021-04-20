package ru.job4j.url_shortcut.models;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sites")
@Data
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany
    private Set<Shortcut> shortcuts = new HashSet<>();

    @ManyToOne
    private Account account;
}
