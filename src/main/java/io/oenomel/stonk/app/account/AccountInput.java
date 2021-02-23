package io.oenomel.stonk.app.account;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountInput implements Serializable {

    private static final long serialVersionUID = 8744075652287698356L;

    private String accountNumber;

    private Long amount;
}
