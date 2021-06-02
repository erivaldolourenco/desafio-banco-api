package com.erivaldo.desafiobancoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erivaldo.desafiobancoapi.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
