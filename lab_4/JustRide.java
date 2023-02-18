class JustRide implements Service {

    private static final int PEAK_HOUR_START = 600;
    private static final int PEAK_HOUR_END = 900;
    private static final int SURCHARGE_FEE = 500;
    private static final int PRICE_PER_KM = 22;

    public int computeFare(int distance, int numPass, int timeOfService) {
        int totalFee = 0;
        if (timeOfService >= PEAK_HOUR_START && timeOfService <= PEAK_HOUR_END) {
            totalFee += SURCHARGE_FEE;
        }
        totalFee += (PRICE_PER_KM * distance);
        return totalFee;
    }

    @Override
    public String toString() {
        return "JustRide";
    }

}
