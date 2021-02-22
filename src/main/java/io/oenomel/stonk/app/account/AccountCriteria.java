package io.oenomel.stonk.app.account;

import io.oenomel.stonk.common.Constants;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountCriteria {

    private Long accountId;

    private Long userId;

    private String accountNumber;

    private Constants.AccountStatus status;
}
