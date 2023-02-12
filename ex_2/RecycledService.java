class RecycledService extends Service {

    private static final int MAINTENANCE_TIME = 60;
    
    RecycledService(Loader loader, Cruise cruise) {
        super(loader, cruise);
    }

    @Override
    public int getServiceEndTime() {
        return super.getServiceEndTime() + MAINTENANCE_TIME;
    }

    @Override
    public boolean completedService(int currTime) {
        return (currTime >= this.getServiceEndTime());
    }

    @Override
    public String toString() {
        return "Recycled " + super.toString();
    }
}
