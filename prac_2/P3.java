class P3 implements Protocol {
    private static final int ISOLATION_PERIOD = 5;
    
    public Protocol next(Person person, Test test, int numOfDays) {
        if (numOfDays <= ISOLATION_PERIOD && !test.isPositive()) {
            return this;
        } else if (person.isHighRisk() && test.isPositive()) {
            return new P1();
        } else if (!person.isHighRisk() && test.isPositive()) {
            return new P2();
        } else {
            return new ProtocolDischarge2();
        }
    }

    @Override
    public String toString() {
        return "P3";
    }
}
