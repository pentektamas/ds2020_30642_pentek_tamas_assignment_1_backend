package ds2020.assignment1.dtos;

import ds2020.assignment1.entities.Account;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class DoctorDTO extends RepresentationModel<DoctorDTO> {

    @NotNull
    private UUID id;
    @NotNull
    private Account account;

    public DoctorDTO() {

    }

    public DoctorDTO(@NotNull UUID id, @NotNull Account account) {
        this.id = id;
        this.account = account;
    }

    public DoctorDTO(@NotNull Account account) {
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
