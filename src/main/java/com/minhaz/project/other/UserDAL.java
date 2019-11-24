package com.minhaz.project.other;

import com.minhaz.project.model.UserModel;
import java.util.List;

public interface UserDAL {

    List<UserModel> getAllUsers();

    UserModel getUserById(String userId);

    UserModel addNewUser(UserModel user);

    Object getAllUserSettings(String userId);

    String getUserSetting(String userId, String key);

    String addUserSetting(String userId, String key, String value);
}
