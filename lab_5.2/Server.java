interface Server {

    // returns server number
    public int getServerNum();

    // returns true if server is not serving any customer
    public boolean isIdle();

    // returns true if server has queue spots
    public boolean hasQueueSpots();

    // returns the restingTime of the server
    public double getRestingTime();

    // Returns a new Server object with the added customer in customerList
    public Server add(Customer customer);

    // Returns a new Server object after removing the customer in customerList
    public Server remove(Server server);

    // Returns a new Server object with the added customer in queueList
    public Server addToQueue(Customer customer);

    // Returns a new Server object after removing the customer in queueList
    public Server removeFromQueue();

    // get available self checkout (for checkout Cluster)
    public int getAvailCounter();

    // returns the server number (for checkout Cluster)
    public Server getFreeServer(int index);

    public boolean isCheckoutCluster();

    public boolean isSelfCheckout();

    // debugging purposes
    public void printQueueList();
}
