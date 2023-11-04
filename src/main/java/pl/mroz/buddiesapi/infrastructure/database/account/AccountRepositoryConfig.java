package pl.mroz.buddiesapi.infrastructure.database.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mroz.buddiesapi.domain.account.AccountRepository;

@Configuration
public class AccountRepositoryConfig {

    @Bean
    AccountRepository accountRepository(AccountRepositoryImpl.AccountJpaRepository jpaRepository,
                                        AccountRepositoryImpl.LocationJpaRepository locationJpaRepository) {
        return new AccountRepositoryImpl(jpaRepository, locationJpaRepository);
    }
}
