package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.ReferralModel.ReferralList;

public class ReferralViewModel implements Parcelable {
    private String id;
    private String TaskId;
    private String FirstName;
    private String LastName;
    private String Name;
    private String MobileNo;
    private String AlternateMobileNo;
    private String Email;
    private String InterestedService;

    public ReferralViewModel() {
        id = "NA";
        TaskId = "NA";
        FirstName = "NA";
        LastName = "NA";
        Name = "NA";
        MobileNo = "NA";
        AlternateMobileNo = "NA";
        Email = "NA";
        InterestedService = "NA";
    }

    protected ReferralViewModel(Parcel in) {
        id = in.readString();
        TaskId = in.readString();
        FirstName = in.readString();
        LastName = in.readString();
        Name = in.readString();
        MobileNo = in.readString();
        AlternateMobileNo = in.readString();
        Email = in.readString();
        InterestedService = in.readString();
    }

    public static final Creator<ReferralViewModel> CREATOR = new Creator<ReferralViewModel>() {
        @Override
        public ReferralViewModel createFromParcel(Parcel in) {
            return new ReferralViewModel(in);
        }

        @Override
        public ReferralViewModel[] newArray(int size) {
            return new ReferralViewModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(TaskId);
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(Name);
        dest.writeString(MobileNo);
        dest.writeString(AlternateMobileNo);
        dest.writeString(Email);
        dest.writeString(InterestedService);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void clone(ReferralList referralList) {
        this.id = referralList.getId();
        this.FirstName = referralList.getFirstName();
        this.LastName = referralList.getLastName();
        this.MobileNo = referralList.getMobileNo();
        this.AlternateMobileNo= referralList.getAlternateMobileNo();
        this.Email = referralList.getEmail();
        this.InterestedService = referralList.getInterestedService();
        this.Name = referralList.getName();

    }

}
