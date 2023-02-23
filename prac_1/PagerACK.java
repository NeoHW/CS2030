public class PagerACK implements Term {
    private final String identifier;
    private final String transmitterIdentifier;

    PagerACK(String identifier, String transmitterIdentifier) {
        this.identifier = identifier;
        this.transmitterIdentifier = transmitterIdentifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public boolean equals(Term other) {
        return this.identifier.equals(other.getIdentifier());
    }

    public String getTransmitterIdentifier() {
        return this.transmitterIdentifier;
    }

    /**
    public Host ack() {
        return new pagerACK(this.transmitterIdentifier, super.getIdentifier());
    }
    */

    @Override
    public String toString() {
        String name = this.identifier;
        return name + " >--snd--> " + this.transmitterIdentifier + " >--rcv--> " + name;
    }
}
