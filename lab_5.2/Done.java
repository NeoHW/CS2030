class Done extends Event {
    
    private final int serverNum;

    Done(double time, Customer customer, int serverNum) {
        super(time, customer);
        this.serverNum = serverNum;
    }

    @Override
    // Do not remove customer here yet as need to account for break
    public Pair<ImList<Event>, ServerList> run(ServerList serverList, PQ<Event> pq) {
        return new Pair<ImList<Event>, ServerList>(
            new ImList<Event>().add(
                new ServerRest(this.time + serverList.get(serverNum).getRestingTime(),
                this.customer, this.serverNum)), serverList);
    }

    @Override
    // returns true at Done Event
    public boolean didServeFinish() {
        return true;
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
        return true;
    }

    @Override
    // returns Done string : e.g. 1.500 1 done serving by 1
    public String toString() {
        return String.format("%s %s done serving by %d\n",
            super.toString(),
            this.customer.toString(),
            (this.serverNum + 1));
    }
}
