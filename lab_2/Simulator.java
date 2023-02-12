class Simulator {
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes;

    Simulator(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
    }

    public String simulate() {
        String output = "";
        int numOfCustomerServed = 0;
        int numOfCustomerLeft = 0;
        ImList<Server> tempServerList = new ImList<Server>();
        
        // adding the max number of servers
        for (int i = 1; i <= numOfServers; i++) {
            tempServerList = tempServerList.add(new Server(i, new ImList<Customer>()));
        }

        // Instantiating a ServerList object from created tempServerList
        ServerList serverList = new ServerList(tempServerList);

        // Instantiating a Prioity Queue pq
        PQ<Event> pq = new PQ<Event>(new EventComp());

        // Adding all the arrivals into the PQ first
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i, arrivalTimes.get(i), serviceTimes.get(i));
            pq = pq.add(new Arrival(arrivalTimes.get(i), customer));
        }

        // Go through the PQ
        while (!pq.isEmpty()) {
            Pair<Event, PQ<Event>> pairFromPQ = pq.poll();
            Event event = pairFromPQ.first();
            pq = pairFromPQ.second();

            // run event using given serverList
            Pair<ImList<Event>, ServerList> pairFromEvent = event.run(serverList);
            ImList<Event> nextEventList = pairFromEvent.first();
            // updates the serverList
            serverList = pairFromEvent.second();

            // add a new event if needed
            if (!nextEventList.isEmpty()) {
                pq = pq.add(nextEventList.get(0));
            }
            
            // attach current event to string output 
            output = output + event;

            // Counting number of Customer served & those who left
            if (event.didServeFinish()) {
                numOfCustomerServed++;
            }

            if (event.leftUnserved()) {
                numOfCustomerLeft++;
            }
        }

        // last line attach to output
        output = output + "[" + numOfCustomerServed + " " + numOfCustomerLeft + "]";
        // return the result
        return output;

        
    }
    
}