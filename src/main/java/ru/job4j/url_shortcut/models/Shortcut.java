package ru.job4j.url_shortcut.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="shortcuts")
@Data
public class Shortcut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="full")
    private String fullUrl;
    @Column(name="short")
    private String shortUrl;
    private Date created;
    @Column(name="called")
    private int calledTimes;

    @ManyToOne
    private Site site;
}
