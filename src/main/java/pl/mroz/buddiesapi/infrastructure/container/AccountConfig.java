package pl.mroz.buddiesapi.infrastructure.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.mroz.buddiesapi.domain.account.AccountDomainService;
import pl.mroz.buddiesapi.domain.account.AccountDomainServiceFactory;
import pl.mroz.buddiesapi.domain.account.AccountRepository;

@Configuration
public class AccountConfig {

    @Bean
    AccountDomainService accountDomainService(AccountRepository repository) {
        return AccountDomainServiceFactory.accountDomainService(repository);
    }
}
