class P1 implements Protocol {

    public Protocol next(Person person, Test test) {
        return test.isPositive() ? new P1() : new ProtocolDischarge();
    }

    @Override
    public String toString() {
        return "P1";
    }
}
