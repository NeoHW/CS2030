class ServiceBegin extends Event {
    
    private final int serverNum;

    ServiceBegin(double time, Customer customer, int serverNum) {
        super(time, customer);
        this.serverNum = serverNum;
    }

    @Override
    public Pair<ImList<Event>, ServerList> run(ServerList serverList) {
        return new Pair<ImList<Event>, ServerList>(
            new ImList<Event>().add(new Service(time, customer, serverNum)), serverList);
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
    // returns ServiceBegin string : e.g. 0.600 2 serves by 2
    public String toString() {
        return String.format("%s %s serves by %d\n",
            super.toString(),
            this.customer.toString(),
            (this.serverNum + 1));
    }
}
