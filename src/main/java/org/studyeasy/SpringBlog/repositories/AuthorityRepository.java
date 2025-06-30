package org.studyeasy.SpringBlog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.studyeasy.SpringBlog.Models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository <Authority,Long>{
    
    Optional<Authority> findByName(String name);

    
}
