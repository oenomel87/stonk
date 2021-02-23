package io.oenomel.stonk.app.account;

import io.oenomel.stonk.common.exception.AccountException;
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

    public Account saveAmount(AccountCriteria criteria) throws AccountException {
        var account = this.accountRepository.findByAccountNumber(criteria.getAccountNumber());

        if(account == null) {
            throw new AccountException("찾을 수 없는 계좌입니다.");
        }

        account.setAmount(account.getAmount() + criteria.getAmount());
        this.accountRepository.save(account);

        return Account.convert(account);
    }

    public Account withdrawalAmount(AccountCriteria criteria) throws AccountException {
        var account = this.accountRepository.findByAccountNumber(criteria.getAccountNumber());

        if(account == null) {
            throw new AccountException("찾을 수 없는 계좌입니다.");
        } else if(account.getAmount() - criteria.getAmount() < 0) {
            throw new AccountException("잔고가 부족합니다.");
        }

        account.setAmount(account.getAmount() - criteria.getAmount());
        this.accountRepository.save(account);

        return Account.convert(account);
    }
}
