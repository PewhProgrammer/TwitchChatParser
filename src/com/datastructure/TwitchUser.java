package com.datastructure;

/**
 * Created by Thinh-Laptop on 18.01.2017.
 */
public class TwitchUser {

    private final String user,bio,id,type;

    public TwitchUser(){user ="";bio="";id="";type="";}

    public TwitchUser(String userName,String userBio, String userID, String userType){
        user = userName;
        bio = userBio;
        id = userID;
        type = userType;
    }

    public String getUser() {
        return user;
    }

    public String getBio() {
        return bio;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
