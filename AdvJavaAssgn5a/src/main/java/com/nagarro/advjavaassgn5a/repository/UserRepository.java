package com.nagarro.advjavaassgn5a.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.advjavaassgn5a.entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {

}
