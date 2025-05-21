package com.example.bankcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankcards.entity.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> 
{
    Optional<User> findByName(String username);
}
