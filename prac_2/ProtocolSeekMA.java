class ProtocolSeekMA implements Protocol {

    public Protocol next(Person person, Test test, int numOfDays) {
        return new ProtocolSeekMA();
    }

    @Override
    public String toString() {
        return "Discharged: seek medical attention";
    }
}
