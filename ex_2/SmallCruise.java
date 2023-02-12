class SmallCruise extends Cruise {

    private static final int NUMOFLOADERS = 1;
    private static final int SERVICETIME = 30;
    
    SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, NUMOFLOADERS, SERVICETIME);
    }
}
