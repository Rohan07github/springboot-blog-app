package org.studyeasy.SpringBlog.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

        for (Privillages auth : Privillages.values()) {
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();

        account01.setEmail("user@user.com");
        account01.setPassword("pass567");
        account01.setFirstname("user");
        account01.setLastname("lastname");
        account01.setAge(23);
        account01.setDate_of_birth(LocalDate.parse("2000-09-04"));
        account01.setGender("Male");

        account02.setEmail("admin@Techroniverseadmin.com");
        account02.setPassword("pass567");
        account02.setFirstname("admin");
        account02.setLastname("lastname");
        account02.setRole(Roles.ADMIN.getRole());
        account02.setAge(50);
        account02.setDate_of_birth(LocalDate.parse("1975-08-05"));
        account02.setGender("Male");

        account03.setEmail("editor@editor.com");
        account03.setPassword("pass567");
        account03.setFirstname("editor");
        account03.setLastname("lastname");
        account03.setRole(Roles.EDITOR.getRole());
        account03.setAge(22);
        account03.setDate_of_birth(LocalDate.parse("2002-03-12"));
        account03.setGender("Female");

        account04.setEmail("super_editor@editor.com");
        account04.setPassword("pass567");
        account04.setFirstname("super_editor");
        account04.setLastname("lastname");
        account04.setRole(Roles.EDITOR.getRole());
        account04.setAge(31);
        account04.setDate_of_birth(LocalDate.parse("1990-10-02"));
        account04.setGender("Male");

        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
        account04.setAuthorities(authorities);

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);

        List<Post> posts = postService.findAll();
        if (posts.size() == 0) {
            Post post01 = new Post();
            post01.setTitle("Spring Boot Overview");
            post01.setBody(
                    """
                                            Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

                            We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need minimal Spring configuration.

                            If you’re looking for information about a specific version, or instructions about how to upgrade from an earlier release, check out the project release notes section on our wiki.

                            Features
                            Create stand-alone Spring applications
                            Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
                            Provide opinionated 'starter' dependencies to simplify your build configuration
                            Automatically configure Spring and 3rd party libraries whenever possible
                            Provide production-ready features such as metrics, health checks, and externalized configuration
                            Absolutely no code generation and no requirement for XML configuration
                             """);
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("MVC Framework Introduction");
            post02.setBody(
                    """
                                <h2>MVC Framework Introduction</h2>

                                <p>
                                    Over the last few years, websites have shifted from simple HTML pages with a bit of CSS
                                    to incredibly complex applications with thousands of developers working on them at the same time.
                                    To work with these complex web applications, developers use different design patterns to lay out their projects,
                                    to make the code less complex and easier to work with. The most popular of these patterns is
                                    <strong>MVC</strong> also known as <em>Model View Controller</em>.
                                </p>

                                <h3>What is MVC?</h3>
                                <p>
                                    The Model-View-Controller (MVC) framework is an architectural/design pattern that separates an application
                                    into three main logical components Model, View, and Controller...
                                </p>

                                <p><img src="https://media.geeksforgeeks.org/wp-content/uploads/20220224160807/Model1.png" alt="MVC Diagram" width="600"/></p>
                                <h4>Controller:</h4>
                            The controller is the component that enables the interconnection between the views and the model so it acts as an intermediary. The controller doesn’t have to worry about handling data logic, it just tells the model what to do. It processes all the business logic and incoming requests, manipulates data using the Model component, and interact with the View to render the final output.
                            <p>
                            Responsibilities:
                            Receiving user input and interpreting it.
                            Updating the Model based on user actions.
                            Selecting and displaying the appropriate View.
                            Example: In a bookstore application, the Controller would handle actions such as searching for a book, adding a book to the cart, or checking out.
                            </p>
                            <h4>View:</h4>
                            <p>
                            The View component is used for all the UI logic of the application. It generates a user interface for the user. Views are created by the data which is collected by the model component but these data aren’t taken directly but through the controller. It only interacts with the controller.

                            Responsibilities:
                            Rendering data to the user in a specific format.
                            Displaying the user interface elements.
                            Updating the display when the Model changes.
                            Example: In a bookstore application, the View would display the list of books, book details, and provide input fields for searching or filtering books.
                            </p>
                            <h4>Model:</h4>
                            <p>
                            The Model component corresponds to all the data-related logic that the user works with. This can represent either the data that is being transferred between the View and Controller components or any other business logic-related data. It can add or retrieve data from the database. It responds to the controller’s request because the controller can’t interact with the database by itself. The model interacts with the database and gives the required data back to the controller.

                            Responsibilities:
                            Managing data: CRUD (Create, Read, Update, Delete) operations.
                            Enforcing business rules.
                            Notifying the View and Controller of state changes.s
                            Example: In a bookstore application, the Model would handle data related to books, such as the book title, author, price, and stock level.
                            </p>
                            """);
            post02.setAccount(account02);
            postService.save(post02);
            Post post03 = new Post();
            post03.setTitle("Model-View-Controller");
            post03.setBody(
                    """
                            <h3><strong>Model-View-Controller</strong></h3>
                            <p><a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/MVC-Process.svg/1200px-MVC-Process.svg.png" alt="MVC Diagram" width="600"></a></p>
                            <p>The Model-View-Controller (MVC) is an architectural pattern commonly used for developing user interfaces.</p>
                            <p><small>Source: <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller">Wikipedia</a></small></p>
                            """);
            post03.setAccount(account01);
            postService.save(post03);

            Post post04 = new Post();
            post04.setTitle("Spring Framework");
            post04.setBody(
                    """
                            <h3><strong>Spring Framework</strong></h3>
                            <p><a href="https://en.wikipedia.org/wiki/Spring_Framework"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/800px-Spring_Framework_Logo_2018.svg.png" alt="Spring Logo" width="300"></a></p>
                            <p>Spring is an application framework and inversion of control container for the Java platform.</p>
                            <p><small>Source: <a href="https://en.wikipedia.org/wiki/Spring_Framework">Wikipedia</a></small></p>
                            """);
            post04.setAccount(account02);
            postService.save(post04);

            Post post05 = new Post();
            post05.setTitle("Model-View-Controller");
            post05.setBody(
                    """
                            <h3><strong>Model-View-Controller</strong></h3>
                            <p><a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/MVC-Process.svg/1200px-MVC-Process.svg.png" alt="MVC Diagram" width="600"></a></p>
                            <p>The Model-View-Controller (MVC) is an architectural pattern commonly used for developing user interfaces that divides the related program logic into three interconnected elements.</p>
                            <p><strong>Model:</strong> The central component that directly manages the data, logic and rules of the application.</p>
                            <p><strong>View:</strong> Any representation of information such as a chart, diagram or table.</p>
                            <p><strong>Controller:</strong> Accepts input and converts it to commands for the model or view.</p>
                            <p>MVC is widely used in web applications where the view is the actual page, the controller is the code processing user actions, and the model is the database and business logic.</p>
                            <p><small>Source: <a href="https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller">Wikipedia</a></small></p>
                            """);
            post05.setAccount(account01);
            postService.save(post05);

            Post post06 = new Post();
            post06.setTitle("Spring Framework");
            post06.setBody(
                    """
                            <h3><strong>Spring Framework</strong></h3>
                            <p><a href="https://en.wikipedia.org/wiki/Spring_Framework"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Spring_Framework_Logo_2018.svg/800px-Spring_Framework_Logo_2018.svg.png" alt="Spring Logo" width="300"></a></p>
                            <p>The Spring Framework is an application framework and inversion of control container for the Java platform.</p>
                            <p>Key features include:</p>
                            <ul>
                              <li><strong>Dependency Injection:</strong> Promotes loose coupling between components</li>
                              <li><strong>AOP (Aspect-Oriented Programming):</strong> Enables cross-cutting concerns</li>
                              <li><strong>Spring MVC:</strong> Web framework built on MVC principles</li>
                              <li><strong>Data Access:</strong> Support for JDBC, ORM frameworks</li>
                              <li><strong>Transaction Management:</strong> Consistent programming model</li>
                            </ul>
                            <p>Spring's core features can be used by any Java application, with extensions for building web applications on top of Java EE.</p>
                            <p><small>Source: <a href="https://en.wikipedia.org/wiki/Spring_Framework">Wikipedia</a></small></p>
                            """);
            post06.setAccount(account02);
            postService.save(post06);
        }
    }
}
