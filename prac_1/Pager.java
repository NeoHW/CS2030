public class Pager implements Term {

    private final String identifier;
    
    Pager(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    public TransmitterRCV snd(TransmitterMid tm) {
        return new TransmitterRCV(tm.getIdentifier(), this.getIdentifier());
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
