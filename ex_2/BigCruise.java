class BigCruise extends Cruise {

    private static final int  LOADER_LENGTH = 40;
    private static final int CUS_SERVED_PER_MIN = 50;
    
    BigCruise(String identifier, int arrivalTime, double length, double numPassengers) { 
        //int serviceTime = (int)Math.ceil(numPassengers/50);
        
        //int numOfLoaders = (int)Math.ceil(length/40);
        
        super(identifier,
            arrivalTime,
            (int)Math.ceil(length / LOADER_LENGTH), // numOfLoaders
            (int)Math.ceil(numPassengers / CUS_SERVED_PER_MIN)); // serviceTime
    }
}
