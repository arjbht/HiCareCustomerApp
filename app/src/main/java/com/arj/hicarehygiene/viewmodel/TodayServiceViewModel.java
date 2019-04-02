package com.arj.hicarehygiene.viewmodel;

public class TodayServiceViewModel {
    private String Order_No;
    private String Service_Plan;
    private String Service_Step;
    private String Slot_Time;


    public String getOrder_No() {
        return Order_No;
    }

    public void setOrder_No(String order_No) {
        Order_No = order_No;
    }

    public String getService_Plan() {
        return Service_Plan;
    }

    public void setService_Plan(String service_Plan) {
        Service_Plan = service_Plan;
    }

    public String getService_Step() {
        return Service_Step;
    }

    public void setService_Step(String service_Step) {
        Service_Step = service_Step;
    }

    public String getSlot_Time() {
        return Slot_Time;
    }

    public void setSlot_Time(String slot_Time) {
        Slot_Time = slot_Time;
    }


}
