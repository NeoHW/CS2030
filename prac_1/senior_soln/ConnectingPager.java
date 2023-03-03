class ConnectingPager implements ConnectingTerm {
    private final String identifier;
    private final String currHost;
    private final ImList<String> hostConnections;

    ConnectingPager(String identifier, String currHost, ImList<String> hostConnections) {
        this.identifier = identifier;
        this.currHost = currHost;
        this.hostConnections = hostConnections;
    }
    
    public String getIdentifier() {
        return this.identifier;
    }

    public String getCurrHost() {
        return this.currHost;
    }

    public ImList<String> getHostConnections() {
        return this.hostConnections;
    }
    
    public boolean equals(Term terminal) {
        if (this == terminal) {
            return true;
        } else {
            return this.getIdentifier() == terminal.getIdentifier();
        }
    }

    public CompleteHost ack() {
        ImList<String> allTerm = this.getHostConnections().add(this.getIdentifier());
        return new Transmitter(this.getCurrHost(), allTerm);
    }

    public String toString() {
        return String.format("%s >--snd--> %s >--rcv--> %s", 
            this.getIdentifier(), this.getCurrHost(), this.getIdentifier());
    }
}
