package io.oenomel.stonk.common.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import io.oenomel.stonk.app.account.Account;
import io.oenomel.stonk.app.account.AccountCriteria;
import io.oenomel.stonk.app.account.AccountService;
import io.oenomel.stonk.app.user.User;
import io.oenomel.stonk.app.user.UserCriteria;
import io.oenomel.stonk.app.user.UserService;
import io.oenomel.stonk.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

    private final UserService userService;

    private final AccountService accountService;

    public User user(Long userId, String name, String email) {
        var criteria = UserCriteria.builder()
                .userId(userId)
                .name(name)
                .email(email)
                .build();
        return this.userService.getUser(criteria);
    }

    public List<Account> accounts(Long userId, String accountNumber, Constants.AccountStatus status) {
        var criteria = AccountCriteria.builder()
                .userId(userId)
                .accountNumber(accountNumber)
                .status(status)
                .build();
        return this.accountService.fetchAccounts(criteria);
    }

    public Account account(Long userId, String accountNumber, Constants.AccountStatus status) {
        var criteria = AccountCriteria.builder()
                .userId(userId)
                .accountNumber(accountNumber)
                .status(status)
                .build();
        return this.accountService.fetchAccount(criteria);
    }
}
