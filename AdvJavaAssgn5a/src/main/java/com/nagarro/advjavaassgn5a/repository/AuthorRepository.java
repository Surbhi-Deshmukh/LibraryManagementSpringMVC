package com.nagarro.advjavaassgn5a.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.nagarro.advjavaassgn5a.entity.Author;


public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	
}
