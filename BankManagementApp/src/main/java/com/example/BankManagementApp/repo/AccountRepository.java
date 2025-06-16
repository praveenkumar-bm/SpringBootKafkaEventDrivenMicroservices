package com.example.BankManagementApp.repo;

import com.example.BankManagementApp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {


}
