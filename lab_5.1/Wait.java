class Wait extends Event {
    private final int serverNum;
    private final double waitingStartTime;
    private final boolean hasStringOutput;

    Wait(double waitingStartTime, double time, Customer customer, int serverNum, boolean output) {
        super(time, customer);
        this.serverNum = serverNum;
        this.waitingStartTime = waitingStartTime;
        this.hasStringOutput = output;
    }

    @Override
    // Links to serviceBegin event
    public Pair<ImList<Event>, ServerList> run(ServerList serverList, PQ<Event> pq) {
        
        ServerList updatedServerList = serverList;
        Server currServer = serverList.get(this.serverNum);
        if (currServer.isIdle()) {
            // calculate total waiting time till now
            double totalWaitingTime = time - waitingStartTime;

            // remove customer from server's Queue
            // BUT not add into queue (done in service event)
            updatedServerList = updatedServerList.removeFromServerQueue(serverNum, customer);

            return new Pair<ImList<Event>, ServerList>(
                    new ImList<Event>().add(
                        new ServiceBegin(time, customer, serverNum, totalWaitingTime)),
                        updatedServerList);
        } else {
            Event nextEvent;
            double nextEventTime;
            int nextEventCustNum;
            // Customer Num in wait event must be > next Event so that it would not be polled by PQ
            PQ<Event> tempPQ = pq;
            while (true) {
                nextEvent = tempPQ.poll().first();
                tempPQ = tempPQ.poll().second();
                nextEventTime = nextEvent.getTime();
                nextEventCustNum = nextEvent.getCustomer().getCustomerNumber();

                if (nextEventCustNum < this.customer.getCustomerNumber()) {
                    break;
                }
            }

            return new Pair<ImList<Event>, ServerList>(
                    new ImList<Event>().add(
                        new Wait(waitingStartTime, nextEventTime, customer, serverNum, false)),
                        serverList);
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
        return this.hasStringOutput;
    }

    @Override
    // returns ServiceBegin string : e.g. 0.600 2 waits at 1
    public String toString() {
        return String.format("%s %s waits at %d\n",
            super.toString(),
            this.customer.toString(),
            (this.serverNum + 1));
    }

}

    
