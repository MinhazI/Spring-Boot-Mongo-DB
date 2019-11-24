package com.minhaz.project.controller;

import com.minhaz.project.model.UserModel;
import com.minhaz.project.other.UserDAL;
import com.minhaz.project.other.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/")
public class UserController {

    private final UserDAL userdal;

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;


    public UserController(UserDAL userdal, UserRepository userRepository) {
        this.userdal = userdal;
        this.userRepository = userRepository;
    }

    //Method to GET all users
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<UserModel> getAllUsers(){
        log.info("Getting all users");

        return userRepository.findAll();
    }

    //Method to ADD new user by ID
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserModel addUser (@RequestBody UserModel user){
        log.info("Adding new user");

        return userRepository.save(user);
    }

    //Method to RETRIEVE particular user settings
    @RequestMapping(value = "/settings/{userId}/{key}", method = RequestMethod.GET)
    public String getUserSettings(@PathVariable String userId, @PathVariable String key){

        return userdal.getUserSetting(userId, key);
    }

    @RequestMapping(value = "/settings/{userId}/{key}/{value}", method = RequestMethod.GET)
    public String addUserSettings(@PathVariable String userId, @PathVariable String key, @PathVariable String value){
//        UserModel user = userRepository.findOne(value)

        UserModel user = (UserModel) userRepository;

        user.getUserSettings().put(key, value);

        userRepository.save(user);

        return "Added new user settings";
    }

}
