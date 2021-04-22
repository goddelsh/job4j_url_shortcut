package ru.job4j.urlshortcut.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.models.Shortcut;

import java.util.List;
import java.util.Optional;

public interface ShortcutRepository extends CrudRepository<Shortcut, Integer> {

    @Query("select s from #{#entityName} s where s.shortUrl = ?1")
    Optional<Shortcut> findByShortUrl(String shortUrl);

    List<Shortcut> findByAccountId(Integer accountId);

    @Modifying
    @Query("update #{#entityName} s set s.calledTimes = s.calledTimes + 1 where s.shortUrl = ?1")
    void incrementCallsStatistic(String shortUrl);
}
