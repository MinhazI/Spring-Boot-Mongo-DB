package com.minhaz.project.other;

import com.minhaz.project.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDalImplemented implements UserDAL {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<UserModel> getAllUsers() {
        return mongoTemplate.findAll(UserModel.class);
    }

    @Override
    public UserModel getUserById(String userId) {
        Query query = new Query();

        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, UserModel.class);
    }

    @Override
    public UserModel addNewUser(UserModel user) {
        mongoTemplate.save(user);

        return user;
    }

    @Override
    public Object getAllUserSettings(String userId) {
        Query query = new Query();

        query.addCriteria(Criteria.where("userId").is(userId));
        UserModel user = mongoTemplate.findOne(query, UserModel.class);

        return user != null ? user.getUserSettings(): "User not found";
    }

    @Override
    public String getUserSetting(String userId, String key) {
        Query query = new Query();
        query.fields().include("userSettings");

        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("userSettings." + key).exists(true)));

        UserModel user = mongoTemplate.findOne(query, UserModel.class);

        return user!=null ? user.getUserSettings().get(key) : "Not found";
    }

    @Override
    public String addUserSetting(String userId, String key, String value) {
        Query query = new Query();

        query.addCriteria(Criteria.where("userId").is(userId));

        UserModel user = mongoTemplate.findOne(query, UserModel.class);

        if (user != null){
            user.getUserSettings().put(key, value);
            mongoTemplate.save(user);

            return "Added new User settings";
        } else {
            return "User not found";
        }
    }
}
