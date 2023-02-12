class Service {
    private final Loader loader;
    private final Cruise cruise;

    Service(Loader loader, Cruise cruise) {
        this.loader = loader;
        this.cruise = cruise;
    }
   
    public int getServiceStartTime() {
        return this.cruise.getArrivalTime();
    }

    public int getServiceEndTime() {
        return this.cruise.getEndTime();
    }

    public boolean completedService(int currTime) {
        return (currTime >= this.cruise.getEndTime());
    }

    public int getLoaderNum() {
        return this.loader.getLoaderNum();
    }

    @Override
    public String toString() {
        return this.loader + " serving " + this.cruise;
    }
    
}
