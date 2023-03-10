import java.util.function.Supplier;

class Server {
    private final int serverNum;
    private final ImList<Customer> customerList;
    private final int maxQueueLength;
    private final ImList<Customer> queueList;
    private final Supplier<Double> restTimes;

    // Constructor
    Server(int serverNum, ImList<Customer> customerList,
        int maxQueueLength, ImList<Customer> queueList, Supplier<Double> restTimes) {
        this.serverNum = serverNum;
        this.customerList = customerList;
        this.maxQueueLength = maxQueueLength;
        this.queueList = queueList;
        this.restTimes = restTimes;
    }

    // returns true if server not serving any customer 
    public boolean isIdle() {
        return (this.customerList.isEmpty());
    }

    // returns true if server has queue spots
    public boolean hasQueueSpots() {
        return (this.queueList.size() < maxQueueLength);
    }

    // returns the restingTime of the server
    public double getRestingTime() {
        return this.restTimes.get();
    }

    // Returns a new Server object with the added customer in customerList
    public Server add(Customer customer) {
        return new Server(this.serverNum, 
            this.customerList.add(customer),
            this.maxQueueLength,
            this.queueList,
            this.restTimes);
    }

    // Returns a new Server object after removing the customer in customerList
    public Server remove() {
        return new Server(this.serverNum, this.customerList.remove(0),
            this.maxQueueLength, this.queueList, this.restTimes);
    }

    // Returns a new Server object with the added customer in queueList
    public Server addToQueue(Customer customer) {
        return new Server(this.serverNum, 
            this.customerList,
            this.maxQueueLength,
            this.queueList.add(customer),
            this.restTimes);
    }

    // Returns a new Server object after removing the customer in queueList
    public Server removeFromQueue() {
        return new Server(this.serverNum, this.customerList,
            this.maxQueueLength, this.queueList.remove(0), this.restTimes);
    }

    @Override
    public String toString() {
        return "server Num: " + serverNum + 
            "\n customer List: " + customerList + "\n queue list: " + queueList;
    }
}
