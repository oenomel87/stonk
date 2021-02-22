package io.oenomel.stonk.app.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager entityManager;

    public UserRepositoryImpl(JPAQueryFactory jpaQueryFactory, EntityManager entityManager) {
        super(UserEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }

    public Optional<List<UserEntity>> fetchUsers(UserCriteria criteria) {
        var user= QUserEntity.userEntity;
        var result = this.jpaQueryFactory.selectFrom(user)
                .where(this.eqUserId(criteria), this.likeName(criteria), this.eqEmail(criteria))
                .fetch();
        return Optional.ofNullable(result);
    }

    public Optional<UserEntity> fetchUser(UserCriteria criteria) {
        var user= QUserEntity.userEntity;
        var result = this.jpaQueryFactory.selectFrom(user)
                .where(this.eqUserId(criteria), this.likeName(criteria), this.eqEmail(criteria))
                .fetchOne();
        return Optional.ofNullable(result);
    }

    private BooleanExpression eqUserId(UserCriteria criteria) {
        var user = QUserEntity.userEntity;
        return criteria.getUserId() != null ? user.userId.eq(criteria.getUserId()) : null;
    }

    private BooleanExpression likeName(UserCriteria criteria) {
        var user = QUserEntity.userEntity;
        return criteria.getName() != null && !criteria.getName().isEmpty()
                ? user.name.like(criteria.getName()) : null;
    }

    private BooleanExpression eqEmail(UserCriteria criteria) {
        var user = QUserEntity.userEntity;
        return criteria.getEmail() != null ? user.email.eq(criteria.getEmail()) : null;
    }
}
