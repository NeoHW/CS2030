class P1 implements Protocol {
    
    // for High Risk Individuals
    public Protocol next(Person person, Test test, int numOfDays) {
        return test.isPositive() ? this : new ProtocolDischarge1();
    }

    @Override
    public String toString() {
        return "P1";
    }
}
