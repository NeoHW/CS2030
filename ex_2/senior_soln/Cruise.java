class Cruise {
    private final String id;
    private final int arrivalTime;
    private final int serviceTime;
    private final int numOfLoaders;
    private static final int MINUTES_PER_HOUR = 60;

    Cruise(String id, int arrivalTime, int numOfLoaders, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.numOfLoaders = numOfLoaders;
        this.serviceTime = serviceTime;
    }

    int toMinutes(int arrivalTime) {
        String time = String.format("%04d", arrivalTime);
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(2));
        return hour * MINUTES_PER_HOUR + min;
    }

    String getId() {
        return this.id;
    }

    int getArrivalTime() {
        return toMinutes(this.arrivalTime);
    }

    int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }

    int getServiceTime() {
        return this.serviceTime;
    }

    @Override
    public String toString() {
        return String.format("%s@%04d", this.id, this.arrivalTime);
    }
}