package ru.job4j.urlshortcut.services;


import org.apache.commons.text.RandomStringGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.models.Account;
import ru.job4j.urlshortcut.models.Shortcut;
import ru.job4j.urlshortcut.repositories.AccountRepository;
import ru.job4j.urlshortcut.repositories.ShortcutRepository;

import java.util.Date;

@Service
public class RegistrationService {

   // final private SiteRepository siteRepository;
    final private AccountRepository accountRepository;
    final private ShortcutRepository shortcutRepository;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationService(//SiteRepository siteRepository,
                               AccountRepository accountRepository,
                               ShortcutRepository shortcutRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
      //  this.siteRepository = siteRepository;
        this.accountRepository = accountRepository;
        this.shortcutRepository = shortcutRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public Account regSite(String url) {
        var lastAccountId = accountRepository.count();
        var account = new Account();
        var passwd = generateRandomSpecialCharacters(4);
        account.setLogin("user" + lastAccountId);
        account.setPassword(bCryptPasswordEncoder.encode(passwd));
        account.setSite(url);
        accountRepository.save(account);

        account.setPassword(passwd);
        return account;

    }

    public Shortcut getShortcut(String url, String login) {
        var result = new Shortcut();
        var account = this.accountRepository.findByLogin(login);
        if (account != null) {
            result.setFullUrl(url);
            result.setShortUrl(generateRandomSpecialCharacters(8));
            result.setCreatedDate(new Date());
            result.setAccount(account);
            result = this.shortcutRepository.save(result);
        }
        return result;
    }

    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange('a', 'z')
                .build();
        return pwdGenerator.generate(length);
    }

}
