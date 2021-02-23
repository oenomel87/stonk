package io.oenomel.stonk.app.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.oenomel.stonk.app.account.AccountEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity implements Serializable {

    private static final long serialVersionUID = -2100469059236342227L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String name;

    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<AccountEntity> accounts;
}
