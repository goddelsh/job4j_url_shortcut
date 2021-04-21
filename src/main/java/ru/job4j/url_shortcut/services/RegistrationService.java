package ru.job4j.url_shortcut.services;


import org.apache.commons.text.RandomStringGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.models.Account;
import ru.job4j.url_shortcut.models.Site;
import ru.job4j.url_shortcut.repositories.AccountRepository;
import ru.job4j.url_shortcut.repositories.ShortcutRepository;
import ru.job4j.url_shortcut.repositories.SiteRepository;

@Service
public class RegistrationService {

    final private SiteRepository siteRepository;
    final private AccountRepository accountRepository;
    final private ShortcutRepository shortcutRepository;
    final private BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationService(SiteRepository siteRepository,
                               AccountRepository accountRepository,
                               ShortcutRepository shortcutRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.siteRepository = siteRepository;
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
        accountRepository.save(account);

        var site = new Site();
        site.setName(url);
        site.setAccount(account);

        siteRepository.save(site);

        account.setPassword(passwd);
        return account;

    }

    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
                .build();
        return pwdGenerator.generate(length);
    }

}
