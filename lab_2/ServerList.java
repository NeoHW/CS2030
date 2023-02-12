class ServerList {
    private final ImList<Server> serverList;

    ServerList(ImList<Server> serverList) {
        this.serverList = serverList;
    }

    public int returnAvailableServer(double currTime) {
        for (int i = 0; i < this.serverList.size(); i++) {
            if (this.serverList.get(i).isAvailable(currTime)) {
                return i;
            }
        }
        return -1;
    }

    // Given a customer and serverNum, add customer to respective server 
        public ServerList addCustomerToServer(int serverNum, Customer customer) {
        Server currServer = this.serverList.get(serverNum);
        currServer = currServer.add(customer);
        return new ServerList(this.serverList.set(serverNum, currServer));
    }

    // Given a server number, remove the current customer
    public ServerList removeCustomerFromServer(int serverNum) {
        Server currServer = this.serverList.get(serverNum);
        currServer = currServer.remove();
        return new ServerList(this.serverList.set(serverNum, currServer));
    }

    public int serverOf(Customer customer) {
        return this.serverList.indexOf(customer) + 1;
    }
}
