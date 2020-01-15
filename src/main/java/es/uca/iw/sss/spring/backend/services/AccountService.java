package es.uca.iw.sss.spring.backend.services;

import es.uca.iw.sss.spring.backend.entities.Account;
import es.uca.iw.sss.spring.backend.entities.User;
import es.uca.iw.sss.spring.backend.repositories.AccountRepository;
import es.uca.iw.sss.spring.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class AccountService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    public AccountService(AccountRepository repo) {
        this.accountRepository = repo;
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public List<Account> listAccount() {
        return accountRepository.findAll();
    }

    public List<Account> findByUser(User user) { return accountRepository.findByUser(user); }

    public Long countAccounts() {
        return accountRepository.count();
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

    public int countByServiceName(String name) { return accountRepository.countByServiceName(name); }


}