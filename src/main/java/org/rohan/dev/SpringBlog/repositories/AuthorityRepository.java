package org.rohan.dev.SpringBlog.repositories;

import java.util.Optional;

import org.rohan.dev.SpringBlog.Models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository <Authority,Long>{
    
    Optional<Authority> findByName(String name);
}
