class ServerList {
    private final ImList<Server> serverList;

    ServerList(ImList<Server> serverList) {
        this.serverList = serverList;
    }

    // returns first available server that is not serving / has spaces in queue or checkoutCluster
    public int returnFirstAvailableServer() {
        // checking for idle servers (no cust)
        for (int i = 0; i < this.serverList.size(); i++) {
            if (serverList.get(i).isIdle()) {
                System.out.println("SERVERLIST: serverList " + i + " is idle");
                return i;
            }
        }
        // then check for 1st server with spots in queue
        for (int i = 0; i < this.serverList.size(); i++) {
            if (serverList.get(i).hasQueueSpots()) {
                System.out.println("SERVERLIST: serverList " + i + " has queue spots");
                return i;
            }
        }
        // else return -1
        System.out.println("SERVERLIST: serverList has no free servers");
        return -1;
    }

    // Returns the server at specified index
    public Server get(int index) {
        return this.serverList.get(index);
    }

    // Adds a server to the ServerList
    public ServerList add(Server server) {
        return new ServerList(this.serverList.add(server));
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

    // Given a server, remove the current customer in customerList
    public ServerList removeCustomerFromServer(Server freeServer) {
        int mainListIndex = freeServer.isCheckoutCluster() ?
            serverList.size() - 1 : serverList.indexOf(freeServer);
        Server currServer = freeServer;
        currServer = currServer.remove();
        return new ServerList(this.serverList.set(mainListIndex, currServer));
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


    // For checkout cluster methods

    // return true if any self-checkouts is free
    public boolean checkoutsFree() {
        for (int i = 0; i < serverList.size(); i++) {
            if (serverList.get(i).isIdle()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.serverList.size(); i++) {
            Server server = serverList.get(i);
            output = output + server;
        }
        return output;
    }
}
