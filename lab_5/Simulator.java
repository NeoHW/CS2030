import java.util.function.Supplier;

class Simulator {
    private final int numServers;
    private final int numSelfChecks;
    private final int maxQueueLength;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTimes;
    private final Supplier<Double> restTimes;

    Simulator(int numServers, int numSelfChecks, int qmax, ImList<Double> arrivalTimes,
        Supplier<Double> serviceTimes, Supplier<Double> restTimes) {
        this.numServers = numServers;
        this.numSelfChecks = numSelfChecks;
        this.maxQueueLength = qmax;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
        this.restTimes = restTimes;
    }

    public String simulate() {
        String output = "";
        int numOfCustomerServed = 0;
        int numOfCustomerLeft = 0;
        double totalWaitingTime = 0;

        ImList<Server> tempServerList = new ImList<Server>();
        
        // adding the max number of servers
        for (int i = 1; i <= numServers; i++) {
            tempServerList = tempServerList.add(
                new Server(i, new ImList<Customer>(), this.maxQueueLength,
                    new ImList<Customer>(), this.restTimes));
        }

        // Instantiating a ServerList object from created tempServerList
        ServerList serverList = new ServerList(tempServerList);

        // Instantiating a Prioity Queue pq
        PQ<Event> pq = new PQ<Event>(new EventComp());

        // Adding all the arrivals into the PQ first
        for (int i = 0; i < arrivalTimes.size(); i++) {
            Customer customer = new Customer(i, arrivalTimes.get(i), serviceTimes);
            pq = pq.add(new Arrival(arrivalTimes.get(i), customer));
        }

        // Go through the PQ
        while (!pq.isEmpty()) {
            Pair<Event, PQ<Event>> pairFromPQ = pq.poll();
            Event event = pairFromPQ.first();
            pq = pairFromPQ.second();

            // run event using given serverList
            Pair<ImList<Event>, ServerList> pairFromEvent = event.run(serverList, pq);
            ImList<Event> nextEventList = pairFromEvent.first();
            // updates the serverList
            serverList = pairFromEvent.second();

            // add a new event if needed
            if (!nextEventList.isEmpty()) {
                pq = pq.add(nextEventList.get(0));
            }
            
            // attach current event to string output 
            if (event.hasStringOutput()) {
                output = output + event;
            }

            // Counting number of Customer served & those who left
            if (event.didServeFinish()) {
                numOfCustomerServed++;
            }

            if (event.leftUnserved()) {
                numOfCustomerLeft++;
            }

            // add waiting time to total waiting time
            totalWaitingTime += event.getWaitingTime();
        }
        
        // if 0 customer served, returns 0 to avoid NaN
        double avgWT = (numOfCustomerServed == 0) ? 0 : (totalWaitingTime / numOfCustomerServed);
        String avgWaitingTime = String.format("%.3f", avgWT);

        // last line attach to output
        output = output + 
            "[" + avgWaitingTime + " " + numOfCustomerServed + " " + numOfCustomerLeft + "]";
        // return the result
        return output;
    }
}
