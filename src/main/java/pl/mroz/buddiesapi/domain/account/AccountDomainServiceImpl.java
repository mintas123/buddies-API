package pl.mroz.buddiesapi.domain.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.mroz.buddiesapi.domain.common.ObjectMapper;

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
        return repository.getById(accountId);
    }

    @Override
    public Account createAccount(Account account) {
        return repository.save(account);
    }

    @Override
    public Account updateAccount(UUID uuid, Account newAccountData) {
        var oldAccountData = getAccount(uuid);
        return repository.save(ObjectMapper.mapInto(newAccountData, oldAccountData));
    }

    @Override
    public void deleteAccount(Account account) {
        repository.delete(account);
    }
}
