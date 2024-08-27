package com.Example.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Example.Employee.entity.Account;
@Repository
public interface CreateAccountRepo extends JpaRepository<Account, Integer> {

	@Query("SELECT u FROM Account u WHERE u.email = ?1")
    Account findByEmail(String email);

}
