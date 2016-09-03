package com.acg.midtermrequirement.model;

import java.util.HashMap;
import java.util.Objects;

/**
 * Created by presciousalago on 02/09/2016.
 */
public class ShoppingList {

    private String listName;
    private String owner;
    private HashMap<String,Object> timestampLastChanged;
    private HashMap<String,Object> timestampCreated;
    private HashMap<String,Object> timestampLastChangeReverse;
    private HashMap<String, Object> userShopping;


    public ShoppingList() {
    }

    public ShoppingList(String listName, String owner, HashMap<String, Object> timestampCreated) {
        this.listName = listName;
        this.owner = owner;
        this.timestampCreated = timestampCreated;
        HashMap<String, Object> timestampNowObject = new HashMap<String, Object>();
//        timestampNowObject.put(Constants.Fire)
    }
}
