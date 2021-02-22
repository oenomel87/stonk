package io.oenomel.stonk.app.user;

import io.oenomel.stonk.app.account.Account;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class User implements Serializable {

    private static final long serialVersionUID = -6944077062990194456L;

    private Long userId;

    private String name;

    private String email;

    private LocalDateTime createdAt;

    private List<Account> accounts;

    public static User convert(UserEntity e) {
        var accounts = e.getAccounts().stream()
                .map(Account::convert)
                .collect(Collectors.toList());
        return User.builder()
                .userId(e.getUserId())
                .name(e.getName())
                .email(e.getEmail())
                .createdAt(e.getCreatedAt())
                .accounts(accounts)
                .build();
    }
}
