package spring.rest.jpa.ormjparest.services;

import org.springframework.stereotype.Service;
import spring.rest.jpa.ormjparest.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import spring.rest.jpa.ormjparest.remotes.AccountRepository;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;



    public Account callSave(Account account) {
        return accountRepository.save(account);
    }

    public  Account callUpdate(Account account){
        return accountRepository.save(account);
    }

    public List<Account> callFindAll(){
        return (List<Account>) accountRepository.findAll();
    }


}
