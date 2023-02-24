public class TransmitterMid implements Host {
    private final String identifier;
    private final ImList<String> connections;

    TransmitterMid(String identifier) {
        this.identifier = identifier;
        this.connections = new ImList<String>();
    }

    TransmitterMid(String identifier, ImList<String> connections) {
        this.identifier = identifier;
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

    public ImList<String> getConnections() {
        return this.connections;
    }

    @Override
    public void broadcast() {
        
    }

    @Override
    public String toString() {
        return this.identifier;
    }
}
