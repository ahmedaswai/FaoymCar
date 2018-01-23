package com.qcar.model.service.result;

public class DashboardResult {

    private DashboardResult(){}
    public static final DashboardResult instance(){
        return new DashboardResult();
    }
    private Long numberOfActiveDrivers;
    private Long numberOfOrders;
    private Long numberOfOnlineDrivers;
    private Long numberOfOnlineUsers;
    private Long numberOfActiveUsers;
    private Long numberOfTrips;

    public Long getNumberOfActiveDrivers() {
        return numberOfActiveDrivers;
    }

    public DashboardResult numberOfActiveDrivers(Long numberOfActiveDrivers) {
        this.numberOfActiveDrivers = numberOfActiveDrivers;
        return this;
    }

    public Long getNumberOfOrders() {
        return numberOfOrders;
    }

    public DashboardResult numberOfOrders(Long numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
        return this;
    }

    public Long getNumberOfOnlineDrivers() {
        return numberOfOnlineDrivers;
    }

    public DashboardResult numberOfOnlineDrivers(Long numberOfOnlineDrivers) {
        this.numberOfOnlineDrivers = numberOfOnlineDrivers;
        return this;
    }

    public Long getNumberOfOnlineUsers() {
        return numberOfOnlineUsers;
    }

    public DashboardResult numberOfOnlineUsers(Long numberOfOnlineUsers) {
        this.numberOfOnlineUsers = numberOfOnlineUsers;
        return this;
    }

    public Long getNumberOfActiveUsers() {
        return numberOfActiveUsers;
    }

    public DashboardResult numberOfActiveUsers(Long numberOfActiveUsers) {
        this.numberOfActiveUsers = numberOfActiveUsers;
        return this;
    }

    public Long getNumberOfTrips() {
        return numberOfTrips;
    }

    public DashboardResult numberOfTrips(Long numberOfTrips) {
        this.numberOfTrips = numberOfTrips;
        return this;
    }
}
