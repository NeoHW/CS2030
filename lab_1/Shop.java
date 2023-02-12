public class Shop {
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes; 

    Shop(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
    }

    private String run() {
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
        
        // customer checking logic
        for (int i = 0; i < arrivalTimes.size(); i++) {
            // format to 3dp decimal to be used in string
            String arrivalTime = String.format("%.3f", arrivalTimes.get(i));
            output = output + arrivalTime + " customer " + (i + 1) + " arrives\n";
            
            // get available Server number
            int availableServer = serverList.returnAvailableServer(arrivalTimes.get(i));
            
            if (availableServer >= 0) {
                serverList = serverList.updateServerList(availableServer, i,
                    arrivalTimes.get(i), serviceTimes.get(i));
                output = output + arrivalTime + 
                    " customer " + (i + 1) + " served by server " + (availableServer + 1) + "\n";
                numOfCustomerServed++;
            } else {
                output = output + arrivalTime + " customer " + (i + 1) + " leaves\n";
                numOfCustomerLeft++;
            }
        }
        
        // last line attach to output
        output = output + "[" + numOfCustomerServed + " " + numOfCustomerLeft + "]";
        // return the result
        return output;
    }




    @Override
    public String toString() {
        return this.run();
    }
}
