class ServiceBooking extends Booking {
    private final Service service;

    ServiceBooking(Driver driver, Request request, Service service) {
        super(driver, request);
        this.service = service;
    }

    public int computeFare() {
        return this.getRequest().computeFare(this.service);
    }

    public ServiceBooking compareTo(ServiceBooking other) {
        if (this.computeFare() == other.computeFare()) {
            return (this.getDriver().getWaitingTime() - other.getDriver().getWaitingTime() < 0) ?
                this : other;
        } else {
            return (this.computeFare() - other.computeFare() < 0) ? this : other;
        }
    }

    @Override
    public String toString() {
        String fare = String.format("%.02f", this.computeFare() / 100.0);
        return "$" + fare + " using " +
            this.getDriver() + " (" + this.service + ")";
    }
}
