class Service {
    private final Loader loader;
    private final Cruise cruise;
    private static final int MINUTES_PER_HOUR = 60;

    Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }

    int toMinutes(int arrivalTime) {
        String time = String.format("%04d", arrivalTime);
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(2));
        return hour * MINUTES_PER_HOUR + min;
    }

    Loader getLoader() {
        return this.loader;
    }

    int getServiceStartTime() {
        return this.cruise.getArrivalTime();
    }

    int getServiceEndTime() {
        return this.getServiceStartTime() + this.cruise.getServiceTime() + 
            this.loader.getRest();
    }

    @Override
    public String toString() {
        return this.loader + " serving " + this.cruise;
    }
}
