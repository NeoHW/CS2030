class Arrival extends Event {

    Arrival(double time, Customer customer) {
        super(time, customer);
    }

    @Override
    public Pair<ImList<Event>, ServerList> run(ServerList serverList) {
        
        ServerList updatedServerList = serverList;
        // get available Server number
        int availableServer = updatedServerList.returnAvailableServer(customer.getArrivalTime());
            
        if (availableServer >= 0) {
            return new Pair<ImList<Event>, ServerList>(
                new ImList<Event>().add(
                    new ServiceBegin(time, customer, availableServer)), serverList);
        } else {
            return new Pair<ImList<Event>, ServerList>(
                new ImList<Event>().add(new Leave(time, customer)), serverList);
        }
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
    // returns arrival string : e.g. 0.500 customer 1 arrives
    public String toString() {
        return String.format("%s %s arrives\n",
            super.toString(),
            this.customer.toString());
    }
}
