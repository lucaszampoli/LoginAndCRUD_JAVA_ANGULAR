package com.zampo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zampo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}

