class ServerRest extends Event {
    
    private final int serverNum;

    ServerRest(double time, Customer customer, int serverNum) {
        super(time, customer);
        this.serverNum = serverNum;
    }

    @Override
    // remove customer here after server finish resting
    public Pair<ImList<Event>, ServerList> run(ServerList serverList, PQ<Event> pq) {
        serverList = serverList.removeCustomerFromServer(serverNum);
        return new Pair<ImList<Event>, ServerList>(new ImList<Event>(), serverList);
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
    // returns ServerRest string : e.g. 1.500 1 finished, server 1 resting
    public String toString() {
        return String.format("%s %s finished, server %d resting\n",
            super.toString(),
            this.customer.toString(),
            (this.serverNum + 1));
    }
}
