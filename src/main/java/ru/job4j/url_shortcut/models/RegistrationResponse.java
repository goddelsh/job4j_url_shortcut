package ru.job4j.url_shortcut.models;

import lombok.Data;

@Data
public class RegistrationResponse {
    private boolean registration = false;
    private String login;
    private String password;
}
