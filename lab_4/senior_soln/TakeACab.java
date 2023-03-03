class TakeACab implements Service {
    private static final int CENTS_PER_KM = 33;
    private static final int BOOKING_FEE = 200;

    TakeACab() {
    }

    @Override
    public int computeFare(int distance, int numOfPassengers, int time) {
        int fare = distance * CENTS_PER_KM;
        fare += BOOKING_FEE;
        return fare;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}