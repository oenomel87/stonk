package io.oenomel.stonk.app.user;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Slf4j
@ActiveProfiles("local")
@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserResolverTests {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;

    @Test
    void userTest() throws IOException {
        var response = this.graphQLTestTemplate.postForResource("graphql/user.graphqls");
        var result = response.readTree().get("data");
        Assertions.assertNotNull(result.asText());
    }
}
