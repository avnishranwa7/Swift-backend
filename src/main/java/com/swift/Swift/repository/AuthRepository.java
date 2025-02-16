package com.swift.Swift.repository;

import com.swift.Swift.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends MongoRepository<User, String> {

    Optional<User> findByEmailId(String emailId);

    Optional<User> findUserByMobileNo(String mobileNo);
}
