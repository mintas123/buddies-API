package pl.mroz.buddiesapi.domain.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class AccountDomainServiceImpl implements AccountDomainService {

    private final AccountRepository repository;

    @Override
    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public Account getAccount(UUID accountId) {
        throw new NotImplementedException();
    }

    @Override
    public void createAccount(Account account) {
        throw new NotImplementedException();
    }

    @Override
    public void updateAccount(Account account) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteAccount(Account account) {
        throw new NotImplementedException();
    }
}
