package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.OrdersModel.Orders;

public class OrderViewModel implements Parcelable {
    private String Id;
    private String OrderNumber;
    private String EndDate;
    private String StartDate;
    private String CustStatus;
    private String HouseID;
    private String HouseName;
    private String UnitValue;
    private String PaymentValue;
    private String AccountId;
    private String AccountName;
    private String AccountMobileNo;
    private String AccountType;
    private String AccountEmail;
    private String AccountFlatNo;
    private String AccountBuildingName;
    private String AccountLandmark;
    private String AccountLocality;
    private String AccountBillingStreet;
    private String AccountBillingPostalCode;
    private String ServicePlanName;
    private String PaymentStatus;
    private String PaymentType;
    private String FlatType;
    private String Quantity;
    private String ServiceType;




    public OrderViewModel() {
        this.Id = "NA";
        this.OrderNumber = "NA";
        this.EndDate = "NA";
        this.StartDate = "NA";
        this.CustStatus = "NA";
        this.HouseID = "NA";
        this.HouseName = "NA";
        this.UnitValue = "NA";
        this.PaymentValue = "NA";
        this.AccountId = "NA";
        this.AccountName = "NA";
        this.AccountMobileNo = "NA";
        this.AccountType = "NA";
        this.AccountEmail = "NA";
        this.AccountFlatNo = "NA";
        this.AccountBuildingName = "NA";
        this.AccountLandmark = "NA";
        this.AccountLocality = "NA";
        this.AccountBillingStreet = "NA";
        this.AccountBillingPostalCode = "NA";
        this.ServicePlanName = "NA";
        this.PaymentStatus = "NA";
        this.PaymentType = "NA";
        this.FlatType = "NA";
        this.Quantity = "NA";
        this.ServiceType ="NA";
    }


    protected OrderViewModel(Parcel in) {
        Id = in.readString();
        OrderNumber = in.readString();
        EndDate = in.readString();
        StartDate = in.readString();
        CustStatus = in.readString();
        HouseID = in.readString();
        HouseName = in.readString();
        UnitValue = in.readString();
        PaymentValue = in.readString();
        AccountId = in.readString();
        AccountName = in.readString();
        AccountMobileNo = in.readString();
        AccountType = in.readString();
        AccountEmail = in.readString();
        AccountFlatNo = in.readString();
        AccountBuildingName = in.readString();
        AccountLandmark = in.readString();
        AccountLocality = in.readString();
        AccountBillingStreet = in.readString();
        AccountBillingPostalCode = in.readString();
        ServicePlanName = in.readString();
        PaymentStatus = in.readString();
        PaymentType = in.readString();
        FlatType = in.readString();
        Quantity = in.readString();
        ServiceType = in.readString();
    }

    public static final Creator<OrderViewModel> CREATOR = new Creator<OrderViewModel>() {
        @Override
        public OrderViewModel createFromParcel(Parcel in) {
            return new OrderViewModel(in);
        }

        @Override
        public OrderViewModel[] newArray(int size) {
            return new OrderViewModel[size];
        }
    };

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        PaymentType = paymentType;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        OrderNumber = orderNumber;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getCustStatus() {
        return CustStatus;
    }

    public void setCustStatus(String custStatus) {
        CustStatus = custStatus;
    }

    public String getHouseID() {
        return HouseID;
    }

    public void setHouseID(String houseID) {
        HouseID = houseID;
    }

    public String getHouseName() {
        return HouseName;
    }

    public void setHouseName(String houseName) {
        HouseName = houseName;
    }

    public String getUnitValue() {
        return UnitValue;
    }

    public void setUnitValue(String unitValue) {
        UnitValue = unitValue;
    }

    public String getPaymentValue() {
        return PaymentValue;
    }

    public void setPaymentValue(String paymentValue) {
        PaymentValue = paymentValue;
    }

    public String getAccountId() {
        return AccountId;
    }

    public void setAccountId(String accountId) {
        AccountId = accountId;
    }

    public String getAccountName() {
        return AccountName;
    }

    public void setAccountName(String accountName) {
        AccountName = accountName;
    }

    public String getAccountMobileNo() {
        return AccountMobileNo;
    }

    public void setAccountMobileNo(String accountMobileNo) {
        AccountMobileNo = accountMobileNo;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public String getAccountEmail() {
        return AccountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        AccountEmail = accountEmail;
    }

    public String getAccountFlatNo() {
        return AccountFlatNo;
    }

    public void setAccountFlatNo(String accountFlatNo) {
        AccountFlatNo = accountFlatNo;
    }

    public String getAccountBuildingName() {
        return AccountBuildingName;
    }

    public void setAccountBuildingName(String accountBuildingName) {
        AccountBuildingName = accountBuildingName;
    }

    public String getAccountLandmark() {
        return AccountLandmark;
    }

    public void setAccountLandmark(String accountLandmark) {
        AccountLandmark = accountLandmark;
    }

    public String getAccountLocality() {
        return AccountLocality;
    }

    public void setAccountLocality(String accountLocality) {
        AccountLocality = accountLocality;
    }

    public String getAccountBillingStreet() {
        return AccountBillingStreet;
    }

    public void setAccountBillingStreet(String accountBillingStreet) {
        AccountBillingStreet = accountBillingStreet;
    }

    public String getAccountBillingPostalCode() {
        return AccountBillingPostalCode;
    }

    public void setAccountBillingPostalCode(String accountBillingPostalCode) {
        AccountBillingPostalCode = accountBillingPostalCode;
    }

    public String getServicePlanName() {
        return ServicePlanName;
    }

    public void setServicePlanName(String servicePlanName) {
        ServicePlanName = servicePlanName;
    }

    public String getFlatType() {
        return FlatType;
    }

    public void setFlatType(String flatType) {
        FlatType = flatType;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void clone(Orders orders) {
        this.Id = orders.getId();
        this.OrderNumber = orders.getOrderNumber();
        this.EndDate = orders.getEndDate();
        this.StartDate = orders.getStartDate();
        this.HouseID = orders.getHouseDetail().getId();
        this.HouseName = orders.getHouseDetail().getName();
        this.UnitValue = orders.getHouseDetail().getUnitValue();
        this.PaymentValue = orders.getPayment();
        this.AccountId = orders.getAccountDetail().getId();
        this.AccountName = orders.getAccountDetail().getName();
        this.AccountMobileNo = orders.getAccountDetail().getMobile();
        this.AccountType = orders.getAccountDetail().getAccountType();
        this.AccountEmail = orders.getAccountDetail().getEmail();
        this.AccountFlatNo = orders.getAccountDetail().getFlatNumber();
        this.AccountBuildingName = orders.getAccountDetail().getBuildingName();
        this.AccountLandmark = orders.getAccountDetail().getLandmark();
        this.AccountLocality = orders.getAccountDetail().getLocalitySuburb();
        this.AccountBillingStreet = orders.getAccountDetail().getBillingStreet();
        this.AccountBillingPostalCode = orders.getAccountDetail().getBillingPoastalCode();
        this.ServicePlanName = orders.getServicePlanName();
        this.PaymentType = orders.getPaymentType();
        this.PaymentStatus = orders.getCustStatus();
        this.FlatType = orders.getFlatType();
        this.Quantity = orders.getQuantity();
        this.CustStatus = orders.getCustStatus();
        this.ServiceType = orders.getServiceType();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(OrderNumber);
        dest.writeString(EndDate);
        dest.writeString(StartDate);
        dest.writeString(CustStatus);
        dest.writeString(HouseID);
        dest.writeString(HouseName);
        dest.writeString(UnitValue);
        dest.writeString(PaymentValue);
        dest.writeString(AccountId);
        dest.writeString(AccountName);
        dest.writeString(AccountMobileNo);
        dest.writeString(AccountType);
        dest.writeString(AccountEmail);
        dest.writeString(AccountFlatNo);
        dest.writeString(AccountBuildingName);
        dest.writeString(AccountLandmark);
        dest.writeString(AccountLocality);
        dest.writeString(AccountBillingStreet);
        dest.writeString(AccountBillingPostalCode);
        dest.writeString(ServicePlanName);
        dest.writeString(PaymentStatus);
        dest.writeString(PaymentType);
        dest.writeString(FlatType);
        dest.writeString(Quantity);
        dest.writeString(ServiceType);
    }
}
