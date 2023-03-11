class ServiceBegin extends Event {
    private final int serverNum;
    private final Server freeServer;
    private final double totalWaitingTime;

    ServiceBegin(double time, Customer customer, int serverNum, Server freeServer,
        double totalWaitingTime) {
        super(time, customer);
        this.serverNum = serverNum;
        this.freeServer = freeServer;
        this.totalWaitingTime = totalWaitingTime;
    }

    @Override
    // Links to service event
    public Pair<ImList<Event>, ServerList> run(ServerList serverList, PQ<Event> pq) {
        Service nextEvent = new Service(time, customer, serverNum, freeServer);
        return new Pair<ImList<Event>, ServerList>(
            new ImList<Event>().add(nextEvent), serverList);
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
    // returns total waiting time from Wait Event
    public double getWaitingTime() {
        return this.totalWaitingTime;
    }

    @Override
    public boolean hasStringOutput() {
        return true;
    }
    
    @Override
    // returns ServiceBegin string : e.g. 0.600 2 serves by 2
    public String toString() {
        return String.format("%s %s serves by %s\n",
            super.toString(),
            this.customer.toString(),
            this.freeServer);
    }
}
