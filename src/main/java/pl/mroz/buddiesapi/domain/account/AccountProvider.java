package pl.mroz.buddiesapi.domain.account;

import java.util.List;
import java.util.UUID;

public interface AccountProvider {

    List<Account> getAllAccounts();

    Account getAccount(UUID accountId);

    void createAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Account account);
}
