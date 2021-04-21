package ru.job4j.urlshortcut.models;

import lombok.Data;

@Data
public class RegistrationResponse {
    private boolean registration = false;
    private String login;
    private String password;
}
