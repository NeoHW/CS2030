class TakeACab implements Service {
    
    private static final int BOOKING_FEE = 200;
    private static final int PRICE_PER_KM = 33;

    public int computeFare(int distance, int numPass, int timeOfService) {
        int totalFee = BOOKING_FEE; 
        totalFee += (PRICE_PER_KM * distance);
        return totalFee;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }

}
