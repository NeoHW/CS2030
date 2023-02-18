class NormalCab implements Driver {
    private final String plateNum;
    private final int waitingTime;
    private final ImList<Service> services;

    NormalCab(String plateNum, int waitingTime) {
        this.plateNum = plateNum;
        this.waitingTime = waitingTime;
        this.services = new ImList<Service>().add(new JustRide()).add(new TakeACab());
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
        return plateNum + " (" + waitingTime + " mins away) NormalCab"; 
    }
}
