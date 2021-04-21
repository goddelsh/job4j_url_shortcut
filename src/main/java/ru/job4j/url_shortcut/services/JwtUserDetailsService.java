package ru.job4j.url_shortcut.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.models.Account;
import ru.job4j.url_shortcut.repositories.AccountRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    final private AccountRepository accountRepository;

    public JwtUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(account.getLogin(), account.getPassword(),
                new ArrayList<>());
    }
}
