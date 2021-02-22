package io.oenomel.stonk.app.account;

import io.oenomel.stonk.common.Constants;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class Account implements Serializable {

    private static final long serialVersionUID = -5060924092857420886L;

    private Long accountId;

    private String accountNumber;

    private Long amount;

    private Constants.AccountStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime suspendedAt;

    public static Account convert(AccountEntity e) {
        return Account.builder()
                .accountId(e.getAccountId())
                .accountNumber(e.getAccountNumber())
                .status(e.getStatus())
                .createdAt(e.getCreatedAt())
                .suspendedAt(e.getSuspendedAt())
                .build();
    }
}
