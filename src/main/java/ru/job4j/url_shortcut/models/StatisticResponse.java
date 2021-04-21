package ru.job4j.url_shortcut.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatisticResponse {
    private String url;
    private Integer total;
}
