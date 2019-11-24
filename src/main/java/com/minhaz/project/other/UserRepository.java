package com.minhaz.project.other;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.minhaz.project.model.UserModel;

@Repository

public interface UserRepository extends MongoRepository<UserModel, String> {
}
