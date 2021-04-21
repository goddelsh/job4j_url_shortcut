package ru.job4j.urlshortcut.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String site;
    private String login;
    private String password;
}
