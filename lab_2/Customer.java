public class Customer {
    private final int customerNumber;
    private final double arrivalTime;
    private final double serviceTime;
    private final double exitTime;

    Customer(int customerNumber, double arrivalTime, double serviceTime) {
        this.customerNumber = customerNumber;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.exitTime = arrivalTime + serviceTime;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getLengthOfStay() {
        return this.serviceTime;
    }

    public double getExitTime() {
        return this.exitTime;
    }

    public boolean isBeingServed(double currTime) {
        return (currTime < this.exitTime);
    }

    @Override
    // returns customer number (index + 1)
    public String toString() {
        return "" + (this.customerNumber + 1);
    }
}
