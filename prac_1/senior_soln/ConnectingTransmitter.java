import java.util.List;

class ConnectingTransmitter implements ConnectingHost {
    private final String identifier;
    private final ImList<String> allTerm;
    private final String currTerm;

    ConnectingTransmitter(String identifier, ImList<String> allTerm, String currTerm) {
        this.identifier = identifier;                                                             
        this.allTerm = allTerm;     
        this.currTerm = currTerm;
    }
  
    public String getIdentifier() {
        return this.identifier;    
    }

    public ImList<String> getAllTerm() {
        return this.allTerm;
    }

    String getCurrTerm() {
        return this.currTerm;
    }

    public boolean equals(Host host) {
        if (this == host) {
            return true;
        } else {
            return this.getIdentifier() == host.getIdentifier();
        }   
    }   

    public ConnectingTerm rcv() {
        String term = this.getCurrTerm();
        return new ConnectingPager(term, this.getIdentifier(), this.getAllTerm());
    }

    public void broadcast() {
        for (String term : allTerm) {
            System.out.println(String.format("%s:beep", term));
        }
    }

    public String toString() {
        return String.format("%s >--snd--> %s", 
                    this.getCurrTerm(), this.getIdentifier());
    }
}
