public interface Term {

    public String getIdentifier();

    public TransmitterRCV snd(Host host);

    public boolean equals(Term other);
}
