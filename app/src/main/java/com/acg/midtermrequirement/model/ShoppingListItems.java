package com.acg.midtermrequirement.model;

/**
 * Created by presciousalago on 02/09/2016.
 */
public class ShoppingListItems {

    private String itemName;
    private String owner;
    private String boughtBy;
    private boolean bought;


    public ShoppingListItems() {
    }

    /**
     * This Consturctor creates new ShoppinListItem
     * gets list item name and owner email as parameters
     * */

    public ShoppingListItems(String itemName, String owner, String boughtBy, boolean bought) {
        this.itemName = itemName;
        this.owner = owner;
        this.boughtBy = boughtBy;
        this.bought = bought;
    }


    public String getItemName() {
        return itemName;
    }

    public String getOwner() {
        return owner;
    }

    public String getBoughtBy() {
        return boughtBy;
    }

    public boolean isBought() {
        return bought;
    }
}
