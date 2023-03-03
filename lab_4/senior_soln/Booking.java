class Booking implements Comparable<Booking> {
    private final Service service;
    private final int fare;
    private final Driver driver;
    private static final double CENTS_PER_DOLLAR = 100.0;
    
    Booking(Driver driver, Request request) {
        this.driver = driver;
        this.service = getBestService(driver, request);
        this.fare = getFare(request, this.service);
    }

    Booking(Driver driver, Request request, Service service) {
        this.driver = driver;
        this.service = service;
        this.fare = getFare(request, service);
    }

    static Service getBestService(Driver driver, Request request) {
        int minFare = Integer.MAX_VALUE;
        Service bestService = new TakeACab();
        for (Service service : driver.getServices()) {
            int fare = request.computeFare(service);
            if (fare < minFare) {
                minFare = fare;
                bestService = service;
            }
        }
        return bestService;
    }

    static int getFare(Request request, Service service) {
        return request.computeFare(service);
    }

    int getWaitingTime() {
        return this.driver.getWaitingTime();
    }

    @Override
    public int compareTo(Booking other) {
        if (this.fare == other.fare) {
            return this.getWaitingTime() - other.getWaitingTime();
        }
        return this.fare - other.fare;
    }

    @Override
    public String toString() {
        double fareInDollars = this.fare / CENTS_PER_DOLLAR;
        return String.format("$%.2f using %s (%s)", 
            fareInDollars, this.driver.toString(), this.service.toString());
    }
}