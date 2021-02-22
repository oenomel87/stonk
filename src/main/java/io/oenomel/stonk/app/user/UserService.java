package io.oenomel.stonk.app.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers(UserCriteria criteria) {
        var result = this.userRepository.fetchUsers(criteria);
        return result.orElse(new ArrayList<>())
                .stream()
                .map(User::convert)
                .collect(Collectors.toList());
    }

    public User getUser(UserCriteria criteria) {
        var result = this.userRepository.fetchUser(criteria);
        return result.map(User::convert)
                .orElse(null);
    }
}
