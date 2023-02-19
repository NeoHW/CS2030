class Booking implements Comparable<Booking> {
    private final Driver driver;
    private final Request request;

    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.request = request;
    }

    public Driver getDriver() {
        return this.driver;
    }

    public Request getRequest() {
        return this.request;
    }


    public Service cheapestService() {
        Service serviceOne = this.driver.getServices().get(0);
        Service serviceTwo = this.driver.getServices().get(1);
        // find out which service is cheaper and returns it
        if (this.request.computeFare(serviceOne) - this.request.computeFare(serviceTwo) < 0) {
            return serviceOne;
        } else {
            return serviceTwo;
        }
    }

    public int computeFare() {
        return this.request.computeFare(this.cheapestService());
    }

    @Override
    public int compareTo(Booking other) {
        if (this.computeFare() == other.computeFare()) {
            return this.driver.getWaitingTime() - other.driver.getWaitingTime();
        } else {
            return this.computeFare() - other.computeFare();
        }
    }

    @Override
    public String toString() {
        String fare = String.format("%.02f", this.computeFare() / 100.0);
        return "$" + fare + " using " +
            this.driver + " (" + this.cheapestService() + ")";
    }
}
