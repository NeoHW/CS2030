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

    public ServerList set(int index, Server server) {
        return new ServerList(this.serverList.set(index, server));
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
        currServer = currServer.remove(currServer);
        return new ServerList(this.serverList.set(serverNum, currServer));
    }

    // Given a server, remove the current customer in customerList
    // only used when server is Self-checkout
    public ServerList removeCustomerFromServer(Server freeServer) {
        int mainListIndex = serverList.size() - 1;
        Server checkoutCluster = this.serverList.get(mainListIndex);
        checkoutCluster = checkoutCluster.remove(freeServer);
        return new ServerList(this.serverList.set(mainListIndex, checkoutCluster));
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

        System.out.print("SERVERLIST: self checkout queue (before): ");
        currServer.printQueueList();

        currServer = currServer.removeFromQueue();

        System.out.print("SERVERLIST: self checkout queue (after): ");
        currServer.printQueueList();

        return new ServerList(this.serverList.set(serverNum, currServer));
    }

    // Returns the customer number
    public int serverOf(Customer customer) {
        return this.serverList.indexOf(customer) + 1;
    }

    public int indexOf(Server server) {
        return this.serverList.indexOf(server);
    }


    // For checkout cluster methods

    // return true if any self-checkouts is free
    // only for self-checkouts
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
