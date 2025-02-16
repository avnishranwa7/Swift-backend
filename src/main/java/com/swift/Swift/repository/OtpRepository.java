package com.swift.Swift.repository;

import com.swift.Swift.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends MongoRepository<User, String> {}
