public class Pager implements Term {

    private final String identifier;
    
    Pager(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public TransmitterRCV snd(Host host) {
        return new TransmitterRCV(host.getIdentifier(), this.getIdentifier());
    }

    @Override
    public boolean equals(Term other) {
        return this.identifier.equals(other.getIdentifier());
    }

    @Override
    public String toString() {
        return this.identifier;
    }
}
