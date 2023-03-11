class ServerRest extends Event {
    private final int serverNum;
    private final Server freeServer;

    ServerRest(double time, Customer customer, int serverNum, Server freeServer) {
        super(time, customer);
        this.serverNum = serverNum;
        this.freeServer = freeServer;
    }

    @Override
    // remove customer here after server finish resting
    public Pair<ImList<Event>, ServerList> run(ServerList serverList, PQ<Event> pq) {
        
        System.out.println("SERVERREST: removing from server "+ freeServer);

        if (freeServer.isSelfCheckout()) {
            serverList = serverList.removeCustomerFromServer(freeServer);
            return new Pair<ImList<Event>, ServerList>(new ImList<Event>(), serverList);
        } else {
            serverList = serverList.removeCustomerFromServer(serverNum);
            return new Pair<ImList<Event>, ServerList>(new ImList<Event>(), serverList);
        }
    }

    @Override
    // returns true at ServerRest Event
    public boolean didServeFinish() {
        return false;
    }

    @Override
    // returns true at Leave Event
    public boolean leftUnserved() {
        return false;
    }

    @Override
    // returns waiting time only at serviceBegin Event
    public double getWaitingTime() {
        return 0;
    }

    @Override
    public boolean hasStringOutput() {
        return false;
    }

    @Override
    // wont be used
    public String toString() {
        return String.format("%s %s finished, server %d resting\n",
            super.toString(),
            this.customer.toString(),
            (this.serverNum + 1));
    }
}
