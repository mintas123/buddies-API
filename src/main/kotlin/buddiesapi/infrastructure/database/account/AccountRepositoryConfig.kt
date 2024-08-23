package buddiesapi.infrastructure.database.account

import buddiesapi.domain.account.AccountRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AccountRepositoryConfig {
    @Bean
    fun accountRepository(jpaRepository: AccountRepositoryImpl.AccountJpaRepository): AccountRepository {
        return AccountRepositoryImpl(jpaRepository)
    }
}
