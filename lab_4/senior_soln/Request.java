class Request {
    private final int distance;
    private final int numOfPassengers;
    private final int time;

    Request(int distance, int numOfPassengers, int time) {
        this.distance = distance;
        this.numOfPassengers = numOfPassengers;
        this.time = time;
    }

    int computeFare(Service service) {
        return service.computeFare(this.distance, this.numOfPassengers, this.time);
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", 
            this.distance, this.numOfPassengers, this.time);
    }
}