class BigCruise extends Cruise {
    private static final int PASSENGERS_SERVED_PER_MIN = 50;
    private static final int METERS_PER_LOADER = 40;
    
    BigCruise(String id, int arrivalTime, int length, int numOfPassengers) {
        super(id, arrivalTime, Math.ceilDiv(length, METERS_PER_LOADER), 
            Math.ceilDiv(numOfPassengers, PASSENGERS_SERVED_PER_MIN));
    }
}
