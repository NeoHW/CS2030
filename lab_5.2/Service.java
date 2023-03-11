class Service extends Event {
    private final int serverNum;
    private final Server freeServer;

    Service(double time, Customer customer, int serverNum, Server freeServer) {
        super(time, customer);
        this.serverNum = serverNum;
        this.freeServer = freeServer;
    }

    @Override
    public Pair<ImList<Event>, ServerList> run(ServerList serverList, PQ<Event> pq) {
        // updates the serverList
        serverList = serverList.addCustomerToServer(this.serverNum, this.customer);

        // add a Done event to the prio queue
        return new Pair<ImList<Event>, ServerList>(
            new ImList<Event>().add(
                new Done(time + customer.getLengthOfStay(), customer, serverNum, freeServer)),
                serverList);
    }

    @Override
    // returns true at Done Event
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
    // no need ouput
    public String toString() {
        return "Service event";
    }
}
