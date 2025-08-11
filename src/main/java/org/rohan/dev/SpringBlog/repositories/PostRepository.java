package org.rohan.dev.SpringBlog.repositories;

import org.rohan.dev.SpringBlog.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post , Long>{

} 
