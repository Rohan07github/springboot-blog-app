package org.rohan.dev.SpringBlog.services;

import java.util.Optional;

import org.rohan.dev.SpringBlog.Models.Authority;
import org.rohan.dev.SpringBlog.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AuthorityService  {
    
    @Autowired
    private AuthorityRepository authorityRepository;

    public Authority save(Authority authority){
        return authorityRepository.save(authority);
    }

    public Optional<Authority> findById(Long id){
        return authorityRepository.findById(id);
    }

    public Optional<Authority> findByName(String name) {
        return authorityRepository.findByName(name);
    }
}
