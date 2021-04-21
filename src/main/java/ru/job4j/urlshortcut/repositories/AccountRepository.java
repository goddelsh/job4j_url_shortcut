package ru.job4j.urlshortcut.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.urlshortcut.models.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByLogin(String login);
}
