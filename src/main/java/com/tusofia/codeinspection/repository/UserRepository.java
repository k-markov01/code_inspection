package com.tusofia.codeinspection.repository;

import java.util.Optional;

import com.tusofia.codeinspection.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Optional<User> findByEmail(String email);
}
