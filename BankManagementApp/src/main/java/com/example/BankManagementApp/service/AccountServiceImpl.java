package com.example.BankManagementApp.service;

import com.example.BankManagementApp.entity.Account;
import com.example.BankManagementApp.repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repo;

    @Override
    public Account createAccount(Account account) {
        Account account_saved = repo.save(account);
        return account_saved;
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account account_found = account.get();
        System.out.println("Account is present");
        return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccounts = repo.findAll();
        return listOfAccounts;
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = account.get();
        Double totalBalance = accountPresent.getAccount_balance() + amount;
        accountPresent.setAccount_balance(totalBalance);
        repo.save(accountPresent);
        return accountPresent;
    }

    @Override
    public Account withdrawAmount(Long accountNumber, Double amount) {
        Optional<Account> account = repo.findById(accountNumber);
        if (account.isEmpty()){
            throw new RuntimeException("Account is not present");
        }
        Account accountPresent = account.get();
        Double totalBalance = accountPresent.getAccount_balance() - amount;
        accountPresent.setAccount_balance(totalBalance);
        repo.save(accountPresent);
        return accountPresent;
    }

    @Override
    public void closeAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber);
        repo.deleteById(accountNumber);

    }
}
