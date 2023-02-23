public class TransmitterMid implements Host {
    private final String identifier;

    TransmitterMid(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public boolean equals(Host other) {
        return this.identifier.equals(other.getIdentifier());
    }

    @Override
    public String toString() {
        return this.identifier;
    }
}
