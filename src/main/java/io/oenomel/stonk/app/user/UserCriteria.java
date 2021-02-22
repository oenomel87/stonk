package io.oenomel.stonk.app.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCriteria {

    private Long userId;

    private String name;

    private String email;
}
