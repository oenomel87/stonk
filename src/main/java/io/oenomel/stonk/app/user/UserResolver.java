package io.oenomel.stonk.app.user;

import graphql.kickstart.tools.GraphQLResolver;
import io.oenomel.stonk.app.account.Account;
import io.oenomel.stonk.app.account.AccountCriteria;
import io.oenomel.stonk.app.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserResolver implements GraphQLResolver<User> {

    private final AccountService accountService;

    public List<Account> accounts(User user) {
        var criteria = AccountCriteria.builder()
                .userId(user.getUserId())
                .build();
        return this.accountService.fetchAccounts(criteria);
    }
}
