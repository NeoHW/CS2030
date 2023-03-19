class CheckoutCluster implements Server {
    private final int serverNum;
    private final int maxQueueLength;
    private final ServerList serverList;
    private final ImList<Customer> queueList;
    private final int numHumanServers;

    // Constructor
    CheckoutCluster(int serverNum, int qmax, ServerList serverList,
        ImList<Customer> queueList, int numHumanServers) {
        this.serverNum = serverNum;
        this.maxQueueLength = qmax;
        this.serverList = serverList;
        this.queueList = queueList;
        this.numHumanServers = numHumanServers;
    }

    // returns checkout counter index that is free
    @Override
    public int getAvailCounter() {
        return this.serverList.returnFirstAvailableServer();
    }

    // returns this server number
    @Override
    public int getServerNum() {
        return this.serverNum;
    }

    // returns true if any self checkout not serving
    @Override
    public boolean isIdle() {
        return (this.serverList.checkoutsFree());
    }

    // returns true if server has queue spots
    @Override
    public boolean hasQueueSpots() {
        return (this.queueList.size() < maxQueueLength);
    }

    // returns the restingTime of the server
    @Override
    public double getRestingTime() {
        return 0.0;
    }

    // Returns a new Checkout cluster with added customer to specific counter
    @Override
    public Server add(Customer customer) {
        int counterNum = this.getAvailCounter();
        ServerList tempServerList = serverList.addCustomerToServer(counterNum, customer);

        return new CheckoutCluster(
            this.serverNum, 
            this.maxQueueLength,
            tempServerList,
            this.queueList,
            this.numHumanServers);
    }

    // need to remove the customer given a selfCheckout Server in CheckOutCluster
    @Override
    public CheckoutCluster remove(Server selfCheckout) {
        int index = selfCheckout.getServerNum() - numHumanServers - 1;
        Server tempSC = this.serverList.get(index);
        tempSC = tempSC.remove(tempSC);
        ServerList tempServerList = this.serverList.set(index, tempSC);

        return new CheckoutCluster(
            this.serverNum, 
            this.maxQueueLength,
            tempServerList,
            this.queueList,
            this.numHumanServers);
    }

    // Returns a new Server object with the added customer in queueList
    @Override
    public Server addToQueue(Customer customer) {
        return new CheckoutCluster(
            this.serverNum, 
            this.maxQueueLength,
            this.serverList,
            this.queueList.add(customer),
            this.numHumanServers);
    }

    // Returns a new Server object after removing the customer in queueList
    @Override
    public Server removeFromQueue() {
        return new CheckoutCluster(
            this.serverNum,
            this.maxQueueLength,
            this.serverList,
            this.queueList.remove(0),
            this.numHumanServers);
    }

    @Override
    public boolean isCheckoutCluster() {
        return true;
    }

    // returns the avail checkout counter in the checkout cluster
    @Override
    public Server getFreeServer(int index) {
        return this.serverList.get(index);
    }

    @Override
    public boolean isSelfCheckout() {
        return true;
    }

    @Override
    public String toString() {
        return "self-check " + this.serverNum;
    }
}