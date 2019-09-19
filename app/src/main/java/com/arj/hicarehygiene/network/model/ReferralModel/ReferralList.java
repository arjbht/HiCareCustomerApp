package com.arj.hicarehygiene.network.model.ReferralModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReferralList {
    @SerializedName("Id") @Expose private String id;
    @SerializedName("FirstName") @Expose private String FirstName;
    @SerializedName("LastName") @Expose private String LastName;
    @SerializedName("Name") @Expose private String Name;
    @SerializedName("Mobile__c") @Expose private String MobileNo;
    @SerializedName("Alternate_Mobile__c") @Expose private String AlternateMobileNo;
    @SerializedName("Email") @Expose private String Email;
    @SerializedName("Interested_Services__c") @Expose private String InterestedService;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getAlternateMobileNo() {
        return AlternateMobileNo;
    }

    public void setAlternateMobileNo(String alternateMobileNo) {
        AlternateMobileNo = alternateMobileNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getInterestedService() {
        return InterestedService;
    }

    public void setInterestedService(String interestedService) {
        InterestedService = interestedService;
    }
}
