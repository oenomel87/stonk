package io.oenomel.stonk.app.user;

import java.util.List;
import java.util.Optional;

public interface UserCustomRepository {

    Optional<UserEntity> fetchUser(UserCriteria criteria);

    Optional<List<UserEntity>> fetchUsers(UserCriteria criteria);
}
