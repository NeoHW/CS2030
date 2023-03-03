interface Host {
    String getIdentifier();

    ImList<String> getAllTerm();

    boolean equals(Host host);

    void broadcast();
}
