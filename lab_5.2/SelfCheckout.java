class SelfCheckout implements Server {
    private static final int RESTING_TIME = 0;
    private final int serverNum;
    private final ImList<Customer> customerList;

    // Constructor
    SelfCheckout(int serverNum, ImList<Customer> customerList) {
        this.serverNum = serverNum;
        this.customerList = customerList;
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

    // wont be used; no queue for self-checkout
    @Override
    public boolean hasQueueSpots() {
        return false;
    }

    // self-checkout resting time is 0
    @Override
    public double getRestingTime() {
        return RESTING_TIME;
    }

    // Returns a new Server object with the added customer in customerList
    @Override
    public Server add(Customer customer) {
        return new SelfCheckout(this.serverNum, this.customerList.add(customer));
    }

    // Returns a new Server object after removing the customer in customerList
    @Override
    public Server remove(Server server) {
        return new SelfCheckout(this.serverNum, this.customerList.remove(0));
    }

    // wont be used; no queue for self-checkout
    @Override
    public Server addToQueue(Customer customer) {
        return this;
    }

    // wont be used; no queue for self-checkout
    @Override
    public Server removeFromQueue() {
        return this;
    }

    @Override
    public boolean isCheckoutCluster() {
        return false;
    }

    @Override
    public boolean isSelfCheckout() {
        return true;
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
    public String toString() {
        return "self-check " + this.serverNum;
    }
}
