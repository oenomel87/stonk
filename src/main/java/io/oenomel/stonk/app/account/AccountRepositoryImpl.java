package io.oenomel.stonk.app.account;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl extends QuerydslRepositorySupport implements AccountCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final EntityManager entityManager;

    public AccountRepositoryImpl(JPAQueryFactory jpaQueryFactory, EntityManager entityManager) {
        super(AccountEntity.class);
        this.jpaQueryFactory = jpaQueryFactory;
        this.entityManager = entityManager;
    }

    public Optional<List<AccountEntity>> fetchAccounts(AccountCriteria criteria) {
        var account = QAccountEntity.accountEntity;
        var result = this.jpaQueryFactory.selectFrom(account)
                .where(this.eqUserId(criteria), this.eqAccountNumber(criteria), this.eqAccountId(criteria))
                .fetch();
        return Optional.of(result);
    }

    public Optional<AccountEntity> fetchAccount(AccountCriteria criteria) {
        var account = QAccountEntity.accountEntity;
        var result = this.jpaQueryFactory.selectFrom(account)
                .where(this.eqUserId(criteria), this.eqAccountNumber(criteria), this.eqAccountId(criteria))
                .fetchOne();
        return Optional.of(result);
    }

    private BooleanExpression eqUserId(AccountCriteria criteria) {
        var account = QAccountEntity.accountEntity;
        return criteria.getUserId() != null ? account.user.userId.eq(criteria.getUserId()) : null;
    }

    private BooleanExpression eqAccountNumber(AccountCriteria criteria) {
        var account = QAccountEntity.accountEntity;
        return criteria.getAccountNumber() != null && !criteria.getAccountNumber().isEmpty()
                ? account.accountNumber.eq(criteria.getAccountNumber()) : null;
    }

    private BooleanExpression eqAccountId(AccountCriteria criteria) {
        var account = QAccountEntity.accountEntity;
        return criteria.getAccountId() != null ? account.accountId.eq(criteria.getAccountId()) : null;
    }
}
