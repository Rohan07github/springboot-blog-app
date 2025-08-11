package org.rohan.dev.SpringBlog.repositories;

import java.util.Optional;

import org.rohan.dev.SpringBlog.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findOneByEmailIgnoreCase(String email);

    @Query("SELECT a FROM Account a WHERE a.password_reset_token = :token")
    Optional<Account> findByToken(@Param("token") String token);
}
