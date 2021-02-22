package io.oenomel.stonk.app.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> fetcchAccounts(AccountCriteria criteria) {
        var result = this.accountRepository.fetchAccounts(criteria);
        return result.orElse(new ArrayList<>())
                .stream()
                .map(Account::convert)
                .collect(Collectors.toList());
    }

    public Account fetchAccount(AccountCriteria criteria) {
        var result = this.accountRepository.fetchAccount(criteria);
        return result.map(Account::convert)
                .orElse(null);
    }
}
