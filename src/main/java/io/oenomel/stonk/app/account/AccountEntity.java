package io.oenomel.stonk.app.account;

import io.oenomel.stonk.app.user.UserEntity;
import io.oenomel.stonk.common.Constants;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Data
public class AccountEntity implements Serializable {

    private static final long serialVersionUID = -8321409028882638748L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "account_number", unique = true, nullable = false)
    private String accountNumber;

    private Long amount;

    @Enumerated(value = EnumType.STRING)
    private Constants.AccountStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "suspended_at")
    private LocalDateTime suspendedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
