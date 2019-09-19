package com.arj.hicarehygiene.viewmodel;

import android.os.Parcel;
import android.os.Parcelable;

import com.arj.hicarehygiene.network.model.OrdersModel.Orders;
import com.arj.hicarehygiene.network.model.OrdersModel.ServicePlan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private String serviceCodeC;
    private String sPCodeC;
    private String serviceGroupCodeC;
    private String activeC;
    private String accountTypeC;
    private String customerTypeR;
    private String serviceAreaR;
    private String subTypeR;
    private String frequencyC;
    private String numberOfTreatmentsC;
    private String validityDaysC;
    private String numberOfCheckupsC;
    private Boolean costSheetServicePlanC;
    private String sPCodePlanName;
    private String idserviceplan;
    private String serviceName;
    private String dataTypeserviceplan;
    private ServicePlan servicePlan;




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
        this.sPCodeC = "NA";
        this.serviceGroupCodeC = "NA";
        this.activeC = "NA";
        this.accountTypeC = "NA";
        this.customerTypeR = "NA";
        this.serviceAreaR = "NA";
        this.subTypeR = "NA";
        this.accountTypeC = "NA";
        this.frequencyC = "NA";
        this.numberOfTreatmentsC = "NA";
        this.validityDaysC = "NA";
        this.numberOfCheckupsC = "NA";
        this.costSheetServicePlanC = false;
        this.sPCodePlanName = "NA";
        this.idserviceplan = "NA";
        this.serviceName = "NA";
        this.dataTypeserviceplan = "NA";
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
        serviceCodeC = in.readString();
        sPCodeC = in.readString();
        serviceGroupCodeC = in.readString();
        activeC = in.readString();
        customerTypeR = in.readString();
        serviceAreaR = in.readString();
        subTypeR = in.readString();
        frequencyC = in.readString();
        numberOfTreatmentsC = in.readString();
        validityDaysC = in.readString();
        numberOfCheckupsC = in.readString();
        byte tmpCostSheetServicePlanC = in.readByte();
        costSheetServicePlanC = tmpCostSheetServicePlanC == 0 ? null : tmpCostSheetServicePlanC == 1;
        sPCodePlanName = in.readString();
        idserviceplan = in.readString();
        serviceName = in.readString();
        dataTypeserviceplan = in.readString();
        servicePlan = in.readParcelable(ServicePlan.class.getClassLoader());
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

    public String getServiceCodeC() {
        return serviceCodeC;
    }

    public void setServiceCodeC(String serviceCodeC) {
        this.serviceCodeC = serviceCodeC;
    }

    public String getsPCodeC() {
        return sPCodeC;
    }

    public void setsPCodeC(String sPCodeC) {
        this.sPCodeC = sPCodeC;
    }

    public String getServiceGroupCodeC() {
        return serviceGroupCodeC;
    }

    public void setServiceGroupCodeC(String serviceGroupCodeC) {
        this.serviceGroupCodeC = serviceGroupCodeC;
    }

    public String getActiveC() {
        return activeC;
    }

    public void setActiveC(String activeC) {
        this.activeC = activeC;
    }

    public Object getAccountTypeC() {
        return accountTypeC;
    }

    public void setAccountTypeC(String accountTypeC) {
        this.accountTypeC = accountTypeC;
    }

    public String getCustomerTypeR() {
        return customerTypeR;
    }

    public void setCustomerTypeR(String customerTypeR) {
        this.customerTypeR = customerTypeR;
    }

    public String getServiceAreaR() {
        return serviceAreaR;
    }

    public void setServiceAreaR(String serviceAreaR) {
        this.serviceAreaR = serviceAreaR;
    }

    public String getSubTypeR() {
        return subTypeR;
    }

    public void setSubTypeR(String subTypeR) {
        this.subTypeR = subTypeR;
    }

    public String getFrequencyC() {
        return frequencyC;
    }

    public void setFrequencyC(String frequencyC) {
        this.frequencyC = frequencyC;
    }

    public String getNumberOfTreatmentsC() {
        return numberOfTreatmentsC;
    }

    public void setNumberOfTreatmentsC(String numberOfTreatmentsC) {
        this.numberOfTreatmentsC = numberOfTreatmentsC;
    }

    public String getValidityDaysC() {
        return validityDaysC;
    }

    public void setValidityDaysC(String validityDaysC) {
        this.validityDaysC = validityDaysC;
    }

    public String getNumberOfCheckupsC() {
        return numberOfCheckupsC;
    }

    public void setNumberOfCheckupsC(String numberOfCheckupsC) {
        this.numberOfCheckupsC = numberOfCheckupsC;
    }

    public Boolean getCostSheetServicePlanC() {
        return costSheetServicePlanC;
    }

    public void setCostSheetServicePlanC(Boolean costSheetServicePlanC) {
        this.costSheetServicePlanC = costSheetServicePlanC;
    }

    public String getsPCodePlanName() {
        return sPCodePlanName;
    }

    public void setsPCodePlanName(String sPCodePlanName) {
        this.sPCodePlanName = sPCodePlanName;
    }

    public String getIdserviceplan() {
        return idserviceplan;
    }

    public void setIdserviceplan(String idserviceplan) {
        this.idserviceplan = idserviceplan;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDataTypeserviceplan() {
        return dataTypeserviceplan;
    }

    public void setDataTypeserviceplan(String dataTypeserviceplan) {
        this.dataTypeserviceplan = dataTypeserviceplan;
    }

    public ServicePlan getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(ServicePlan servicePlan) {
        this.servicePlan = servicePlan;
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
        this.serviceAreaR =orders.getServicePlan().getServiceAreaR();
        this.serviceCodeC = orders.getServicePlan().getServiceCodeC();
        this.serviceName = orders.getServicePlan().getName();
        this.activeC = orders.getServicePlan().getActiveC();
        this.customerTypeR =orders.getServicePlan().getCustomerTypeR();
        this.numberOfCheckupsC = orders.getServicePlan().getNumberOfCheckupsC();
        this.dataTypeserviceplan = orders.getServicePlan().getDataType();
        this.servicePlan = orders.getServicePlan();
        this.serviceGroupCodeC = orders.getServicePlan().getServiceGroupCodeC();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Id);
        parcel.writeString(OrderNumber);
        parcel.writeString(EndDate);
        parcel.writeString(StartDate);
        parcel.writeString(CustStatus);
        parcel.writeString(HouseID);
        parcel.writeString(HouseName);
        parcel.writeString(UnitValue);
        parcel.writeString(PaymentValue);
        parcel.writeString(AccountId);
        parcel.writeString(AccountName);
        parcel.writeString(AccountMobileNo);
        parcel.writeString(AccountType);
        parcel.writeString(AccountEmail);
        parcel.writeString(AccountFlatNo);
        parcel.writeString(AccountBuildingName);
        parcel.writeString(AccountLandmark);
        parcel.writeString(AccountLocality);
        parcel.writeString(AccountBillingStreet);
        parcel.writeString(AccountBillingPostalCode);
        parcel.writeString(ServicePlanName);
        parcel.writeString(PaymentStatus);
        parcel.writeString(PaymentType);
        parcel.writeString(FlatType);
        parcel.writeString(Quantity);
        parcel.writeString(ServiceType);
        parcel.writeString(serviceCodeC);
        parcel.writeString(sPCodeC);
        parcel.writeString(serviceGroupCodeC);
        parcel.writeString(activeC);
        parcel.writeString(customerTypeR);
        parcel.writeString(serviceAreaR);
        parcel.writeString(subTypeR);
        parcel.writeString(frequencyC);
        parcel.writeString(numberOfTreatmentsC);
        parcel.writeString(validityDaysC);
        parcel.writeString(numberOfCheckupsC);
        parcel.writeByte((byte) (costSheetServicePlanC == null ? 0 : costSheetServicePlanC ? 1 : 2));
        parcel.writeString(sPCodePlanName);
        parcel.writeString(idserviceplan);
        parcel.writeString(serviceName);
        parcel.writeString(dataTypeserviceplan);
        parcel.writeParcelable(servicePlan, i);
    }
}
