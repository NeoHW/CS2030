public class ConnectedTransmitter extends Transmitter {
    private final String pagerIdentifier;
    private final ImList<String> connections;

    ConnectedTransmitter(String identifier, String pagerIdentifier, ImList<String> connections) {
        super(identifier);
        this.pagerIdentifier = pagerIdentifier;
        this.connections = connections.add(pagerIdentifier);
    }

    public String getPagerIdentifier() {
        return this.pagerIdentifier;
    }

    public ImList<String> getConnections() {
        return this.connections;
    }

    @Override
    public void broadcast() {
        for (int i = 0; i < connections.size(); i++) {
            System.out.println(connections.get(i) + ":beep");
        }
    }

    @Override
    public String toString() {
        return this.pagerIdentifier + " >--snd--> " + super.getIdentifier() + 
            " >--rcv--> " + this.pagerIdentifier + 
            " >--ack--> " + super.getIdentifier();
    }
}
