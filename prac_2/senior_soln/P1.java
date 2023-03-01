class P1 implements Protocol {

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (!test.isValid()) {
            return this;
        }

        if (person.isHighRisk()) {
            return test.isPositive() ? this : new Discharged(DischargedStatus.FOLLOW_UP);
        }

        return test.isPositive() ? new P2() : new Discharged(DischargedStatus.FOLLOW_UP);
    }

    @Override
    public String toString() {
        return "P1";
    }
}
