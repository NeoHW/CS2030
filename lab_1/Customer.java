public class Customer {
    private final int customerNumber;
    private final double arrivalTime;
    private final double exitTime;

    Customer(int customerNumber, double arrivalTime, double lengthOfStay) {
        this.customerNumber = customerNumber;
        this.arrivalTime = arrivalTime;
        this.exitTime = arrivalTime + lengthOfStay;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getExitTime() {
        return this.exitTime;
    }

    public boolean isBeingServed(double currTime) {
        return (currTime < this.exitTime);
    }
}
