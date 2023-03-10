import java.util.function.Supplier;

public class Customer {
    private final int customerNumber;
    private final double arrivalTime;
    private final Supplier<Double> serviceTime;

    Customer(int customerNumber, double arrivalTime, Supplier<Double> serviceTime) {
        this.customerNumber = customerNumber;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    public double getArrivalTime() {
        return this.arrivalTime;
    }

    public double getLengthOfStay() {
        return this.serviceTime.get();
    }

    public double getExitTime() {
        return this.arrivalTime + this.getLengthOfStay();
    }

    public boolean isBeingServed(double currTime) {
        return (currTime < this.getExitTime());
    }

    @Override
    // returns customer number (index + 1)
    public String toString() {
        return "" + (this.customerNumber + 1);
    }
}
