package ru.job4j.url_shortcut.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.url_shortcut.models.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByLogin(String login);
}
