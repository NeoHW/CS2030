public class TransmitterRCV implements Host {
    private final String identifier;
    private final String pagerIdentifier;

    TransmitterRCV(String identifier, String pagerIdentifier) {
        this.identifier = identifier;
        this.pagerIdentifier = pagerIdentifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    public String getPagerIdentifier() {
        return this.pagerIdentifier;
    }

    @Override
    public boolean equals(Host other) {
        return this.identifier.equals(other.getIdentifier());
    }

    public PagerACK rcv() {
        return new PagerACK(this.pagerIdentifier, this.identifier);
    }

    @Override
    public String toString() {
        return this.pagerIdentifier + " >--snd--> " + this.identifier;
    }
}
