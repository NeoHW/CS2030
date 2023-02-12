class Server {
    private final int serverNum;
    private final ImList<Customer> customerList;

    // Note: Each server can only serve a maximum of one customer

    // Constructor
    Server(int serverNum, ImList<Customer> customerList) {
        this.serverNum = serverNum;
        this.customerList = customerList;
    }

    // Overloaded : Returns a new Server object with the added customer in ImList
    public Server add(Customer customer) {
        return new Server(this.serverNum, this.customerList.add(customer));
    }

    public Server remove() {
        return new Server(this.serverNum, this.customerList.remove(0));
    }

    // Check to see if server is available 
    boolean isAvailable(double currTime) {
        if (this.customerList.isEmpty()) {
            return true;
        } else if (this.customerList.get(0).isBeingServed(currTime)) {
            return false;
        } else {
            return true;
        }
    }

}