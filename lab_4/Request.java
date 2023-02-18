public class Request {

    private final int distance;
    private final int numPass;
    private final int timeOfService;

    Request(int distance, int numPass, int timeOfService) {
        this.distance = distance;
        this.numPass = numPass;
        this.timeOfService = timeOfService;
    }

    public int computeFare(Service service) {
        return service.computeFare(distance, numPass, timeOfService);
    }

    @Override
    public String toString() {
        return distance + "km" + " for " + numPass + "pax @ " + timeOfService + "hrs";
    }
}
