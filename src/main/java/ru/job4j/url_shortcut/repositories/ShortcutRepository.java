package ru.job4j.url_shortcut.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.models.Shortcut;

public interface ShortcutRepository extends CrudRepository<Shortcut, Integer> {
}
