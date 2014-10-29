package com.bryanpotts.mondayschild;

import java.io.Serializable;

/**
 * Created by bryan on 29/10/14.
 */
public class mcRSSDataItem implements Serializable{

    // *****************
    // Declare Variables
    // *****************
// region
    private String itemTitle;
    private String itemDesc;
    private String itemLink;
// endregion

    // ***************************
    // Declare Getters and Setters
    // ***************************
// region
    public String getItemTitle() {
        return this.itemTitle;
    }

    public void setItemTitle(String sItemTitle) {
        this.itemTitle = sItemTitle;
    }

    public String getItemDesc() {
        return this.itemDesc;
    }

    public void setItemDesc(String sItemDesc) {
        this.itemDesc = sItemDesc;
    }

    public String getItemLink() {
        return  this.itemLink;
    }

    public void setItemLink(String sItemLink) {
        this.itemLink = sItemLink;
    }
// endregion

    // *******************
    // Declare constructor
    // *******************
// region
    public mcRSSDataItem() {
        this.itemTitle = "";
        this.itemDesc = "";
        this.itemLink = "";
    }

    @Override
    public String toString() {
        String starSignRSSHoroscopeData;
        starSignRSSHoroscopeData = "mcRSSDataItem [itemTitle=" + itemTitle;
        starSignRSSHoroscopeData = ", itemDesc=" + itemDesc;
        starSignRSSHoroscopeData = ", itemLink=" + itemLink + "]";

        return starSignRSSHoroscopeData;
    }
// endregion


}
