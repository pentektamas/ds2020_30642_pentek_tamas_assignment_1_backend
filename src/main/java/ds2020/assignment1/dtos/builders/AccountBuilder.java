package ds2020.assignment1.dtos.builders;

import ds2020.assignment1.dtos.AccountDTO;
import ds2020.assignment1.entities.Account;

public class AccountBuilder {

    private AccountBuilder() {

    }

    public static AccountDTO toAccountDTO(Account account) {
        return new AccountDTO(account.getId(), account.getUserName(), account.getPassword(), account.getAccountType());
    }

    public static Account toAccountEntity(AccountDTO accountDTO) {
        return new Account(accountDTO.getId(), accountDTO.getUserName(), accountDTO.getPassword(), accountDTO.getAccountType());
    }
}
