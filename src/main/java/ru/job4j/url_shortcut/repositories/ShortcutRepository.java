package ru.job4j.url_shortcut.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.models.Shortcut;

import java.util.List;
import java.util.Optional;

public interface ShortcutRepository extends CrudRepository<Shortcut, Integer> {

    @Query("select s from #{#entityName} s where s.shortUrl = ?1")
    Optional<Shortcut> findByShortUrl(String shortUrl);

    List<Shortcut> findByAccountId(Integer account_id);
}
