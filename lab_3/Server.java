class Server {
    private final int serverNum;
    private final ImList<Customer> customerList;
    private final int maxQueueLength;
    private final ImList<Customer> queueList;

    // Constructor
    Server(int serverNum, ImList<Customer> customerList,
        int maxQueueLength, ImList<Customer> queueList) {
        this.serverNum = serverNum;
        this.customerList = customerList;
        this.maxQueueLength = maxQueueLength;
        this.queueList = queueList;
    }

    // returns true if server not serving any customer 
    public boolean isIdle() {
        return (this.customerList.isEmpty());
    }

    // returns true if server has queue spots
    public boolean hasQueueSpots() {
        return (this.queueList.size() < maxQueueLength);
    }

    // Returns a new Server object with the added customer in customerList
    public Server add(Customer customer) {
        return new Server(this.serverNum, 
            this.customerList.add(customer),
            this.maxQueueLength,
            this.queueList);
    }

    // Returns a new Server object after removing the customer in customerList
    public Server remove() {
        return new Server(this.serverNum, this.customerList.remove(0),
            this.maxQueueLength, this.queueList);
    }

    // Returns a new Server object with the added customer in queueList
    public Server addToQueue(Customer customer) {
        return new Server(this.serverNum, 
            this.customerList,
            this.maxQueueLength,
            this.queueList.add(customer));
    }

    // Returns a new Server object after removing the customer in queueList
    public Server removeFromQueue() {
        return new Server(this.serverNum, this.customerList,
            this.maxQueueLength, this.queueList.remove(0));
    }

    @Override
    public String toString() {
        return "server Num: " + serverNum + 
            "\n customer List: " + customerList + "\n queue list: " + queueList;
    }
}
