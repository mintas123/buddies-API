package pl.mroz.buddiesapi.domain.account;

public class AccountDomainServiceFactory {

    public static AccountDomainService accountDomainService(AccountRepository repository) {
        return new AccountDomainServiceImpl(repository);
    }
}
