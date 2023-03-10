abstract class Event {
    protected final double time;
    protected final Customer customer;
    // protected final Server server;
    // protected final ServerList serverList;

    Event(double time, Customer customer) {
        this.time = time;
        this.customer = customer;
    }

    public double getTime() {
        return this.time;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    // To tell if customer is served
    abstract boolean didServeFinish();

    // To tell if customer left due to max capacity
    abstract boolean leftUnserved();

    // Get the waiting time from waiting in queue to being served
    abstract double getWaitingTime();

    // run this event and return a new event code for the next event
    abstract Pair<ImList<Event>, ServerList> run(ServerList serverList, PQ<Event> pq);

    // to tell if string output should be added to the logs
    abstract boolean hasStringOutput();

    @Override
    // returns the time of event
    public String toString() {
        String output = String.format("%.3f", this.time);
        return output;
    }
    
}
