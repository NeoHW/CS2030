class Cruise {
    private final String identifier;
    private final int arrivalTime;
    private final int serviceTime;
    private final int numOfLoaders;

    private static final int MINUTES = 60;

    Cruise(String identifier, int arrivalTime, int numOfLoaders, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoaders = numOfLoaders;
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return this.serviceTime; 
    }

    public int getArrivalTime() {
        // 100 will be 60 , 130 will be 90
        int tempTime = this.arrivalTime;
        int output = 0;
        while (tempTime >= 100) {
            output += MINUTES;
            tempTime -= 100;
        }
        output += tempTime;
        return output;
    }

    public int getEndTime() {
        return this.getArrivalTime() + this.serviceTime; 
    }


    public int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }

    @Override
    public String toString() {
        return this.identifier + "@" + String.format("%04d", this.arrivalTime); 
    }
}
