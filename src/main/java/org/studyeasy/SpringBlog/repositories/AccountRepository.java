package org.studyeasy.SpringBlog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.studyeasy.SpringBlog.Models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findOneByEmailIgnoreCase(String email);

    @Query("SELECT a FROM Account a WHERE a.password_reset_token = :token")
    Optional<Account> findByToken(@Param("token") String token);
}
