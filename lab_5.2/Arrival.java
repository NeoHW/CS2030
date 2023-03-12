class Arrival extends Event {

    Arrival(double time, Customer customer) {
        super(time, customer);
    }

    @Override
    public Pair<ImList<Event>, ServerList> run(ServerList serverList, PQ<Event> pq) {
        // get available Server number 
        int availableServerNum = serverList.returnFirstAvailableServer();
        
        if (availableServerNum >= 0) {
            Server currServer = serverList.get(availableServerNum);

            // get self-checkout free server if it happens to be checkout cluster
            if (currServer.isCheckoutCluster()) {
                // returns -1 if no server, so need to join queue
                int indexFreeServer = currServer.getAvailCounter();
                
                if (indexFreeServer == -1) {
                    // wait event for Self Checkout Cluster
                    serverList = serverList.addToServerQueue(availableServerNum, customer);
                    return new Pair<ImList<Event>, ServerList>(
                        new ImList<Event>().add(
                            new Wait(time, time, customer, availableServerNum, true)),
                            serverList);
                } else {
                    // immediate add on self checkout counter
                    Server freeServer = currServer.getFreeServer(indexFreeServer);
                    return new Pair<ImList<Event>, ServerList>(
                        new ImList<Event>().add(
                            new ServiceBegin(time, customer, availableServerNum, freeServer, 0)),
                            serverList);
                }
            }

            // original code for human servers
            // add a ServiceBegin event if server is not serving & has no queue
            if (currServer.isIdle()) {
                return new Pair<ImList<Event>, ServerList>(
                    new ImList<Event>().add(
                        new ServiceBegin(time, customer, availableServerNum, currServer, 0)),
                        serverList);
            } else {
                // add a Wait event and add customer to sever's queue
                serverList = serverList.addToServerQueue(availableServerNum, customer);
                return new Pair<ImList<Event>, ServerList>(
                    new ImList<Event>().add(
                        new Wait(time, time, customer, availableServerNum, true)),
                        serverList);
            }
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
    // returns waiting time only at serviceBegin Event
    public double getWaitingTime() {
        return 0;
    }

    @Override
    public boolean hasStringOutput() {
        return true;
    }
    
    @Override
    // returns arrival string : e.g. 0.500 customer 1 arrives
    public String toString() {
        return String.format("%s %s arrives\n",
            super.toString(),
            this.customer.toString());
    }
}
