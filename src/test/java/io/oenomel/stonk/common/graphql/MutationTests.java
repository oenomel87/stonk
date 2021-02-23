package io.oenomel.stonk.common.graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import io.oenomel.stonk.app.account.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Transactional
@ActiveProfiles("local")
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MutationTests {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void saveTest() throws IOException {
        var response = this.graphQLTestTemplate.postForResource("graphql/accountSave.graphqls");
        var result = response.readTree().get("data").get("save");
        var accountNumber = result.get("accountNumber").asText();
        var amount = result.get("amount").asLong();

        var accountEntity = this.accountRepository.findByAccountNumber(accountNumber);
        Assertions.assertEquals(amount, accountEntity.getAmount());
    }
}
