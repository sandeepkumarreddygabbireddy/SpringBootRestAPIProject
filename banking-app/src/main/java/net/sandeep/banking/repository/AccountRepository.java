package net.sandeep.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.sandeep.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	

}
