package pl.mroz.buddiesapi.domain.account;

public class AccountProviderFactory {

    public static AccountProvider accountProvider(AccountRepository repository) {
        return new AccountProviderImpl(repository);
    }
}
