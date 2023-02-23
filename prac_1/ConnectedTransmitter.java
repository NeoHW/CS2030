public class ConnectedTransmitter extends Transmitter {
    private final String pagerIdentifier;
    
    ConnectedTransmitter(String identifier, String pagerIdentifier) {
        super(identifier);
        this.pagerIdentifier = pagerIdentifier;
    }

    @Override
    public String toString() {
        return this.pagerIdentifier + " >--snd--> " + super.getIdentifier() + 
            " >--rcv--> " + this.pagerIdentifier + 
            " >--ack--> " + super.getIdentifier();
    }
}
