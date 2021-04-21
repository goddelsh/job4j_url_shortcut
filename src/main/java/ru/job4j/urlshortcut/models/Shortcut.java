package ru.job4j.urlshortcut.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shortcuts")
@Data
public class Shortcut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_url")
    private String fullUrl;
    @Column(name = "short_url")
    private String shortUrl;
    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "called_times")
    private int calledTimes;

    @ManyToOne
    private Account account;
}
