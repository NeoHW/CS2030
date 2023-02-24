public class TransmitterRCV implements Host {
    private final String identifier;
    private final String pagerIdentifier;
    private final ImList<String> connections;

    TransmitterRCV(String identifier, String pagerIdentifier, ImList<String> connections) {
        this.identifier = identifier;
        this.pagerIdentifier = pagerIdentifier;
        this.connections = connections;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public boolean equals(Host other) {
        return this.identifier.equals(other.getIdentifier());
    }

    public String getPagerIdentifier() {
        return this.pagerIdentifier;
    }

    public PagerACK rcv() {
        return new PagerACK(this.pagerIdentifier, this.identifier, this.connections);
    }

    @Override
    public void broadcast() {
        for (int i = 0; i < connections.size(); i++) {
            System.out.println(connections.get(i) + ":beep");
        }
    }

    @Override
    public String toString() {
        return this.pagerIdentifier + " >--snd--> " + this.identifier;
    }
}
