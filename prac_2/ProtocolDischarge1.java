class ProtocolDischarge1 implements Protocol {

    public Protocol next(Person person, Test test, int numOfDays) {
        return test.isPositive() ? new ProtocolSeekMA() : this;
    }

    @Override
    public String toString() {
        return "Discharged: follow up with doctor";
    }
}
