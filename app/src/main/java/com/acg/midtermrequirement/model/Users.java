package com.acg.midtermrequirement.model;

import java.util.HashMap;

/**
 * Created by presciousalago on 02/09/2016.
 */
public class Users {


    private String name;
    private String email;
    private HashMap<String,Object> timstampJoined;
    private boolean hasLoggedInWIthPassword;

    public Users() {
    }

    public Users(String name, String email, HashMap<String, Object> timstampJoined, boolean hasLoggedInWIthPassword) {
        this.name = name;
        this.email = email;
        this.timstampJoined = timstampJoined;
        this.hasLoggedInWIthPassword = hasLoggedInWIthPassword;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, Object> getTimstampJoined() {
        return timstampJoined;
    }

    public boolean isHasLoggedInWIthPassword() {
        return hasLoggedInWIthPassword;
    }
}
