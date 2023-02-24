public interface Host {

    public String getIdentifier();

    public boolean equals(Host other);

    public void broadcast();
}
