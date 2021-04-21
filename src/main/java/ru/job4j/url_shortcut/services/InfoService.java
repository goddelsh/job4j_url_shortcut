package ru.job4j.url_shortcut.services;


import org.springframework.stereotype.Service;
import ru.job4j.url_shortcut.repositories.AccountRepository;
import ru.job4j.url_shortcut.repositories.ShortcutRepository;

@Service
public class InfoService {

    final private ShortcutRepository shortcutRepository;
    final private AccountRepository accountRepository;


    public InfoService(ShortcutRepository shortcutRepository, AccountRepository accountRepository) {
        this.shortcutRepository = shortcutRepository;
        this.accountRepository = accountRepository;
    }

    public String getFullUrl(String shortUrl) {
        String result = null;
        var shortcut = shortcutRepository.findByShortUrl(shortUrl).orElse(null);
        if (shortcut != null) {
            shortcut.setCalledTimes(shortcut.getCalledTimes() + 1);
            shortcutRepository.save(shortcut);
            result = shortcut.getFullUrl();
        }
        return result;
    }
}
