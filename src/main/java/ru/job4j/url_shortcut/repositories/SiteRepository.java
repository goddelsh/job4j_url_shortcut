package ru.job4j.url_shortcut.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.models.Site;

public interface SiteRepository extends CrudRepository<Site, Integer> {
}
