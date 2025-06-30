package org.studyeasy.SpringBlog.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.studyeasy.SpringBlog.Models.Account;
import org.studyeasy.SpringBlog.Models.Authority;
import org.studyeasy.SpringBlog.Models.Post;
import org.studyeasy.SpringBlog.services.AccountService;
import org.studyeasy.SpringBlog.services.AuthorityService;
import org.studyeasy.SpringBlog.services.PostService;
import org.studyeasy.SpringBlog.util.constants.Privillages;
import org.studyeasy.SpringBlog.util.constants.Roles;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        // Seed privileges if not already present
        for (Privillages privilege : Privillages.values()) {
            authorityService.findById(privilege.getId())
                    .orElseGet(() -> {
                        Authority authority = new Authority();
                        authority.setId(privilege.getId());
                        authority.setName(privilege.getPrivillage());
                        return authorityService.save(authority);
                    });
        }

        // Seed accounts only if DB is empty
        if (accountService.findAll().isEmpty()) {

            Account account01 = new Account();
            account01.setEmail("user@user.com");
            account01.setPassword("pass567");
            account01.setFirstname("user");
            account01.setLastname("lastname");
            account01.setAge(23);
            account01.setDate_of_birth(LocalDate.parse("2000-09-04"));
            account01.setGender("Male");

            Account account02 = new Account();
            account02.setEmail("admin@Techroniverseadmin.com");
            account02.setPassword("pass567");
            account02.setFirstname("admin");
            account02.setLastname("lastname");
            account02.setRole(Roles.ADMIN.getRole());
            account02.setAge(50);
            account02.setDate_of_birth(LocalDate.parse("1975-08-05"));
            account02.setGender("Male");

            Account account03 = new Account();
            account03.setEmail("editor@editor.com");
            account03.setPassword("pass567");
            account03.setFirstname("editor");
            account03.setLastname("lastname");
            account03.setRole(Roles.EDITOR.getRole());
            account03.setAge(22);
            account03.setDate_of_birth(LocalDate.parse("2002-03-12"));
            account03.setGender("Female");

            Account account04 = new Account();
            account04.setEmail("super_editor@editor.com");
            account04.setPassword("pass567");
            account04.setFirstname("super_editor");
            account04.setLastname("lastname");
            account04.setRole(Roles.EDITOR.getRole());
            account04.setAge(31);
            account04.setDate_of_birth(LocalDate.parse("1990-10-02"));
            account04.setGender("Male");

            // Add specific privileges to account04
            Set<Authority> authorities = new HashSet<>();
            authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
            authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
            account04.setAuthorities(authorities);

            accountService.save(account01);
            accountService.save(account02);
            accountService.save(account03);
            accountService.save(account04);

            // Seed posts for users
            Post post01 = new Post();
            post01.setTitle("Spring Boot Overview");
            post01.setBody(
                    "Spring Boot makes it easy to create stand-alone, production-grade Spring-based applications...");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("MVC Framework Introduction");
            post02.setBody("<h2>MVC Framework Introduction</h2><p>...</p>");
            post02.setAccount(account02);
            postService.save(post02);

            Post post03 = new Post();
            post03.setTitle("Model-View-Controller");
            post03.setBody("<h3><strong>Model-View-Controller</strong></h3><p>...</p>");
            post03.setAccount(account01);
            postService.save(post03);

            Post post04 = new Post();
            post04.setTitle("Spring Framework");
            post04.setBody("<h3><strong>Spring Framework</strong></h3><p>...</p>");
            post04.setAccount(account02);
            postService.save(post04);

            Post post05 = new Post();
            post05.setTitle("Model-View-Controller");
            post05.setBody("<h3><strong>Model-View-Controller</strong></h3><p>...</p>");
            post05.setAccount(account01);
            postService.save(post05);

            Post post06 = new Post();
            post06.setTitle("Spring Framework");
            post06.setBody("<h3><strong>Spring Framework</strong></h3><p>...</p>");
            post06.setAccount(account02);
            postService.save(post06);
        }
    }
}
