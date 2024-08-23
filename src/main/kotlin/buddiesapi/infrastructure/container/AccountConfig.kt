package buddiesapi.infrastructure.container

import buddiesapi.domain.account.AccountDomainService
import buddiesapi.domain.account.AccountDomainServiceFactory
import buddiesapi.domain.account.AccountRepository
import org.springframework.context.annotation.Bean

class AccountConfig {
    @Bean
    fun accountDomainService(repository: AccountRepository): AccountDomainService {
        return AccountDomainServiceFactory.accountDomainService(repository)
    }
}
