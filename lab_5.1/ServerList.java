class ServerList {
    private final ImList<Server> serverList;

    ServerList(ImList<Server> serverList) {
        this.serverList = serverList;
    }

    // returns first available server that is not serving / has spaces in queue
    public int returnFirstAvailableServer() {
        // checking for idle servers (no cust)
        for (int i = 0; i < this.serverList.size(); i++) {
            if (serverList.get(i).isIdle()) {
                return i;
            }
        }
        // then check for 1st server with spots in queue
        for (int i = 0; i < this.serverList.size(); i++) {
            if (serverList.get(i).hasQueueSpots()) {
                return i;
            }
        }
        // else return -1
        return -1;
    }

    // Returns the server at specified index
    public Server get(int index) {
        return this.serverList.get(index);
    }

    // Given a customer and serverNum, add customer to respective server 
    public ServerList addCustomerToServer(int serverNum, Customer customer) {
        Server currServer = this.serverList.get(serverNum);
        currServer = currServer.add(customer);
        return new ServerList(this.serverList.set(serverNum, currServer));
    }

    // Given a server number, remove the current customer in customerList
    public ServerList removeCustomerFromServer(int serverNum) {
        Server currServer = this.serverList.get(serverNum);
        currServer = currServer.remove();
        return new ServerList(this.serverList.set(serverNum, currServer));
    }

    // Given a customer and serverNum, add customer to respective server queue
    public ServerList addToServerQueue(int serverNum, Customer customer) {
        Server currServer = this.serverList.get(serverNum);
        currServer = currServer.addToQueue(customer);
        return new ServerList(this.serverList.set(serverNum, currServer));
    }

    // Given a customer and serverNum, remove customer from respective server queue
    public ServerList removeFromServerQueue(int serverNum, Customer customer) {
        Server currServer = this.serverList.get(serverNum);
        currServer = currServer.removeFromQueue();
        return new ServerList(this.serverList.set(serverNum, currServer));
    }

    // Returns the customer number
    public int serverOf(Customer customer) {
        return this.serverList.indexOf(customer) + 1;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.serverList.size(); i++) {
            output = output + serverList.get(i);
        }
        return output;
    }
}
