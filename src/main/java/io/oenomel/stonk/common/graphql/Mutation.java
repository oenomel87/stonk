package io.oenomel.stonk.common.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import io.oenomel.stonk.app.account.Account;
import io.oenomel.stonk.app.account.AccountCriteria;
import io.oenomel.stonk.app.account.AccountInput;
import io.oenomel.stonk.app.account.AccountService;
import io.oenomel.stonk.common.exception.AccountException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

    private final AccountService accountService;

    public Account save(AccountInput input) throws AccountException {
        var criteria = AccountCriteria.builder()
                .accountNumber(input.getAccountNumber())
                .amount(input.getAmount())
                .build();
        return this.accountService.saveAmount(criteria);
    }

    public Account withdrawal(AccountInput input) throws AccountException {
        var criteria = AccountCriteria.builder()
                .accountNumber(input.getAccountNumber())
                .amount(input.getAmount())
                .build();
        return this.accountService.withdrawalAmount(criteria);
    }
}
