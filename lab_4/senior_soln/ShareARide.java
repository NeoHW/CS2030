class ShareARide implements Service {
    private static final int CENTS_PER_KM = 50;
    private static final int PEAK_START = 600;
    private static final int PEAK_END = 900;
    private static final int SURCHARGE = 500;
    
    ShareARide() {
    }

    @Override
    public int computeFare(int distance, int numOfPassengers, int time) {
        int fare = distance * CENTS_PER_KM;
        if (time >= PEAK_START && time <= PEAK_END) {
            fare += SURCHARGE;
        }
        return fare / numOfPassengers;
    }

    @Override
    public String toString() {
        return "ShareARide";
    }
}