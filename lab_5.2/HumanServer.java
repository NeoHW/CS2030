import java.util.function.Supplier;

class HumanServer implements Server {
    private final int serverNum;
    private final ImList<Customer> customerList;
    private final int maxQueueLength;
    private final ImList<Customer> queueList;
    private final Supplier<Double> restTimes;

    // Constructor
    HumanServer(int serverNum, ImList<Customer> customerList,
        int maxQueueLength, ImList<Customer> queueList, Supplier<Double> restTimes) {
        this.serverNum = serverNum;
        this.customerList = customerList;
        this.maxQueueLength = maxQueueLength;
        this.queueList = queueList;
        this.restTimes = restTimes;
    }

    // returns this server number
    @Override
    public int getServerNum() {
        return this.serverNum;
    }

    // returns true if server not serving any customer 
    @Override
    public boolean isIdle() {
        return (this.customerList.isEmpty());
    }

    // returns true if server has queue spots
    @Override
    public boolean hasQueueSpots() {
        return (this.queueList.size() < maxQueueLength);
    }

    // returns the restingTime of the server
    @Override
    public double getRestingTime() {
        return this.restTimes.get();
    }

    // Returns a new Server object with the added customer in customerList
    @Override
    public Server add(Customer customer) {
        return new HumanServer(this.serverNum, 
            this.customerList.add(customer),
            this.maxQueueLength,
            this.queueList,
            this.restTimes);
    }

    // Returns a new Server object after removing the customer in customerList
    @Override
    public Server remove(Server server) {
        return new HumanServer(this.serverNum, this.customerList.remove(0),
            this.maxQueueLength, this.queueList, this.restTimes);
    }

    // Returns a new Server object with the added customer in queueList
    @Override
    public Server addToQueue(Customer customer) {
        return new HumanServer(this.serverNum, 
            this.customerList,
            this.maxQueueLength,
            this.queueList.add(customer),
            this.restTimes);
    }

    // Returns a new Server object after removing the customer in queueList
    @Override
    public Server removeFromQueue() {
        return new HumanServer(this.serverNum, this.customerList,
            this.maxQueueLength, this.queueList.remove(0), this.restTimes);
    }

    @Override
    public boolean isCheckoutCluster() {
        return false;
    }

    // only for CheckoutCluster
    @Override
    public int getAvailCounter() {
        return -1;
    }

    @Override
    public Server getFreeServer(int index) {
        return this;
    }

    @Override
    public boolean isSelfCheckout() {
        return false;
    }

    // for debugging purposes
    public void printQueueList() {
        System.out.println(queueList);
    }

    @Override
    public String toString() {
        return "" + this.serverNum;
    }
}
