class Service extends Event {
    
    private final int serverNum;

    Service(double time, Customer customer, int serverNum) {
        super(time, customer);
        this.serverNum = serverNum;
    }

    @Override
    public Pair<ImList<Event>, ServerList> run(ServerList serverList) {
        // updates the serverList
        serverList = serverList.addCustomerToServer(this.serverNum, this.customer);

        // add a Done event to the prio queue
        return new Pair<ImList<Event>, ServerList>(
            new ImList<Event>().add(
                new Done(this.time + this.customer.getLengthOfStay(), this.customer,
                this.serverNum)), serverList);
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
    // no need ouput
    public String toString() {
        return "";
    }
}
