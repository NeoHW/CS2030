interface ConnectingTerm extends Term {
    String getCurrHost();

    ImList<String> getHostConnections();

    CompleteHost ack();
}
