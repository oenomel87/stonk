package io.oenomel.stonk.app.account;

import java.util.List;
import java.util.Optional;

public interface AccountCustomRepository {

    Optional<List<AccountEntity>> fetchAccounts(AccountCriteria criteria);

    Optional<AccountEntity> fetchAccount(AccountCriteria criteria);
}
