package ds2020.assignment1.dtos;

import ds2020.assignment1.entities.AccountType;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AccountDTO extends RepresentationModel<AccountDTO> {

    private UUID id;
    private String userName;
    @NotNull
    private String password;
    @NotNull
    private AccountType accountType;

    public AccountDTO() {

    }

    public AccountDTO(UUID id, String userName, @NotNull String password, @NotNull AccountType accountType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.accountType = accountType;
    }

    public AccountDTO(String userName, @NotNull String password, @NotNull AccountType accountType) {
        this.userName = userName;
        this.password = password;
        this.accountType = accountType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
