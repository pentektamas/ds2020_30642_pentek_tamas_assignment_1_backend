package ds2020.assignment1.services;

import ds2020.assignment1.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ds2020.assignment1.dtos.AccountDTO;
import ds2020.assignment1.dtos.builders.AccountBuilder;
import ds2020.assignment1.entities.Account;
import ds2020.assignment1.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> findAccounts() {
        List<Account> accountList = accountRepository.findAll();
        return accountList.stream()
                .map(AccountBuilder::toAccountDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO findAccountById(UUID accountId) {
        Optional<Account> foundAccount = accountRepository.findById(accountId);
        if (!foundAccount.isPresent()) {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id: " + accountId);
        }
        return AccountBuilder.toAccountDTO(foundAccount.get());
    }


    public AccountDTO findAccountByUsernameAndPassword(String username, String password) {
        Optional<Account> foundAccount = accountRepository.findAccountByUserNameAndPassword(username, password);
        if (!foundAccount.isPresent()) {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id: " + username);
        }
        return AccountBuilder.toAccountDTO(foundAccount.get());
    }

    public UUID insertAccount(AccountDTO accountDTO) {
        Account account = AccountBuilder.toAccountEntity(accountDTO);
        account = accountRepository.save(account);
        return account.getId();
    }

    public UUID updateAccount(UUID accountId, AccountDTO accountDTO) {
        Optional<Account> foundAccount = accountRepository.findById(accountId);
        if (!foundAccount.isPresent()) {
            throw new ResourceNotFoundException(Account.class.getSimpleName() + " with id: " + accountId);
        }
        Account account = foundAccount.get();
        account.setId(accountDTO.getId());
        account.setAccountType(accountDTO.getAccountType());
        account.setUserName(accountDTO.getUserName());
        account.setPassword(accountDTO.getPassword());
        account = accountRepository.save(account);
        return account.getId();
    }

    public void deleteAccount(UUID accountId) {
        accountRepository.deleteById(accountId);
    }
}
