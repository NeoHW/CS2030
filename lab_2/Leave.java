class Leave extends Event {

    Leave(double time, Customer customer) {
        super(time, customer);
    }

    @Override
    public Pair<ImList<Event>, ServerList> run(ServerList serverList) {
        return new Pair<ImList<Event>, ServerList>(new ImList<Event>(), serverList);
    }

    @Override
    // returns true at Done Event
    public boolean didServeFinish() {
        return false;
    }

    @Override
    // returns true at Leave Event
    public boolean leftUnserved() {
        return true;
    }

    @Override
    // returns arrival string : e.g. 0.700 3 leaves
    public String toString() {
        return String.format("%s %s leaves\n",
            super.toString(),
            this.customer.toString());
    }
}
