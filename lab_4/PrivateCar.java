class PrivateCar implements Driver {
    private final String plateNum;
    private final int waitingTime;
    private final ImList<Service> services;

    PrivateCar(String plateNum, int waitingTime) {
        this.plateNum = plateNum;
        this.waitingTime = waitingTime;
        this.services = new ImList<Service>().add(new JustRide()).add(new ShareARide());
    }

    @Override
    public int getWaitingTime() {
        return this.waitingTime;
    }

    @Override
    public ImList<Service> getServices() {
        return this.services;
    }

    @Override
    public String toString() {
        return plateNum + " (" + waitingTime + " mins away) PrivateCar"; 
    }
}
