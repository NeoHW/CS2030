class Transmitter implements CompleteHost {
    private final String identifier;
    private final ImList<String> allTerm;

    Transmitter(String identifier, ImList<String> allTerm) {
        this.identifier = identifier;
        this.allTerm = allTerm;
    }

    Transmitter(String identifier) {
        this(identifier, new ImList<String>());
    }

    public String getIdentifier() {
        return this.identifier;    
    }

    public ImList<String> getAllTerm() {
        return this.allTerm;
    }
 
    public boolean equals(Host host) {
        if (this == host) {
            return true;
        } else {
            return this.getIdentifier() == host.getIdentifier();
        }
    }

    public void broadcast() {
        for (String term : allTerm) {
            System.out.println(String.format("%s:beep", term));
        }
    }

    public String toString() {
        if (this.getAllTerm().isEmpty()) {
            return this.getIdentifier();
        } else {
            String lastTerm = this.getAllTerm().get(this.getAllTerm().size() - 1);
            return String.format("%s >--snd--> %s >--rcv--> %s >--ack--> %s", 
                lastTerm, this.getIdentifier(), lastTerm, this.getIdentifier());
        }
    }
}
