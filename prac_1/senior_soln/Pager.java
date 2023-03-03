class Pager implements Term {
    private final String identifier;

    Pager(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return this.identifier;
    }
    
    public boolean equals(Term terminal) {
        if (this == terminal) {
            return true;
        } else {
            return this.getIdentifier() == terminal.getIdentifier();
        }
    }

    ConnectingHost snd(CompleteHost host) {
        return new ConnectingTransmitter(host.getIdentifier(), host.getAllTerm(),
            this.getIdentifier());
    }

    public String toString() {
        return this.getIdentifier();
    }
}
