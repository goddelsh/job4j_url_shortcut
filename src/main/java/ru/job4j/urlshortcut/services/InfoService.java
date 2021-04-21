package ru.job4j.urlshortcut.services;


import org.springframework.stereotype.Service;
import ru.job4j.urlshortcut.models.StatisticResponse;
import ru.job4j.urlshortcut.repositories.AccountRepository;
import ru.job4j.urlshortcut.repositories.ShortcutRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<StatisticResponse> getStatistic(String user) {
        List<StatisticResponse> result = new ArrayList<>();
        var acount = accountRepository.findByLogin(user);
        if (acount != null) {
            result = shortcutRepository.findByAccountId(acount.getId())
                    .stream()
                    .map(el -> new StatisticResponse(el.getFullUrl(), el.getCalledTimes()))
                    .collect(Collectors.toList());
        }
        return result;
    }
}
