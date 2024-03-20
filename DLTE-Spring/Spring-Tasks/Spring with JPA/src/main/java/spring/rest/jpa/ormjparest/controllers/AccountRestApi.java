package spring.rest.jpa.ormjparest.controllers;
/*
AccountOpenning>> POST mapping
AccountUpdate>> PUT mapping
List<Account>> GET mapping
 */

import org.springframework.web.bind.annotation.*;
import spring.rest.jpa.ormjparest.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import spring.rest.jpa.ormjparest.services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountRestApi {

    @Autowired
    AccountService accountService;
    @PostMapping("/")
    public Account apiSave(@RequestBody Account account){
        return accountService.callSave(account);
    }
    @PutMapping("/{id}")
    public  Account apiUpdate(@PathVariable Long id,@RequestBody Account apiUpdate){
        apiUpdate.setAccNo(id);
        return accountService.callUpdate(apiUpdate);
    }

    @GetMapping("/")
    public List<Account> apiAll(){
        return accountService.callFindAll();
    }
}
