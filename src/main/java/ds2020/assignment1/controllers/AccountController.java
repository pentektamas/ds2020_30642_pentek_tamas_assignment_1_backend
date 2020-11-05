package ds2020.assignment1.controllers;

import ds2020.assignment1.dtos.AccountDTO;
import ds2020.assignment1.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/loginCredentials/{user}/{id}")
    public ResponseEntity<AccountDTO> getAccountByCredentials(@PathVariable("user") String username, @PathVariable("id") String password) {
        AccountDTO dto = accountService.findAccountByUsernameAndPassword(username, password);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
