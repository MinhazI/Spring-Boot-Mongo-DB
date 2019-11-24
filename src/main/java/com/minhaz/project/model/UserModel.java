package com.minhaz.project.model;

import java.util.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserModel {

    @Id
    private String userID;
    private String userName;
    private Date creationDate = new Date();
    private Map<String, String> userSettings = new HashMap<>();

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Map<String, String> getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(Map<String, String> userSettings) {
        this.userSettings = userSettings;
    }
}
