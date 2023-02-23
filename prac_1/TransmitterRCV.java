public class TransmitterRCV extends Transmitter {
    private final String pagerIdentifier;

    TransmitterRCV(String identifier, String pagerIdentifier) {
        super(identifier);
        this.pagerIdentifier = pagerIdentifier;
    }

    public String getPagerIdentifier() {
        return this.pagerIdentifier;
    }

    @Override
    public String toString() {
        return this.pagerIdentifier + " >--snd--> " + super.toString();
    }
}
