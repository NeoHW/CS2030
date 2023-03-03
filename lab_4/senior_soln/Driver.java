abstract class Driver {
    private final String license;
    private final int waitingTime;
    private final ImList<Service> services;
    
    Driver(String license, int waitingTime, ImList<Service> services) {
        this.license = license;
        this.waitingTime = waitingTime;
        this.services = services;
    }

    ImList<Service> getServices() {
        return this.services;
    }

    int getWaitingTime() {
        return this.waitingTime;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away)", this.license, this.waitingTime);
    }
}