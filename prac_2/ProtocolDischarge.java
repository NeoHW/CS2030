class ProtocolDischarge implements Protocol {

    public Protocol next(Person person, Test test) {
        return test.isPositive() ? new ProtocolSeekMA() : new ProtocolDischarge();
    }

    @Override
    public String toString() {
        return "Discharged: follow up with doctor";
    }
}
