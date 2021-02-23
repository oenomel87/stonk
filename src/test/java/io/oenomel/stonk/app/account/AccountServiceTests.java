package io.oenomel.stonk.app.account;

import io.oenomel.stonk.common.exception.AccountException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Slf4j
@ActiveProfiles("local")
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountServiceTests {

    @Autowired
    private AccountService accountService;

    @Autowired
    private EntityManager entityManager;

    @Test
    void saveAmountTest() throws AccountException {
        var criteria = AccountCriteria.builder()
                .accountNumber("123")
                .amount(100L)
                .build();
        this.accountService.saveAmount(criteria);

        this.entityManager.close();

        var account = this.accountService.fetchAccount(criteria);
        Assertions.assertEquals(100L, account.getAmount());
    }
}
