class ServerList {
    private final ImList<Server> serverList;

    ServerList(ImList<Server> serverList) {
        this.serverList = serverList;
    }

    public int returnAvailableServer(double currTime) {
        for (int i = 0; i < this.serverList.size(); i++) {
            if (this.serverList.get(i).isAvailable(currTime)) {
                return i;
            }
        }
        return -1;
    }

    public ServerList updateServerList(int serverNum, int customerNumber, double arrivalTime,
        double serviceTime) {
        Server currServer = this.serverList.get(serverNum);
        currServer = currServer.add(customerNumber, arrivalTime, serviceTime);
        return new ServerList(this.serverList.set(serverNum, currServer));
    }
}
