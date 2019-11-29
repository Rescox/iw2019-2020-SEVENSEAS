package es.uca.iw.sss.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public Long countAccounts() {
        return accountRepository.count();
    }

    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }
}