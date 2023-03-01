class P2 implements Protocol {
    private static final int ISOLATION_PERIOD = 3;
    private static final int VACC_MAX_DAYS = 7;
    private static final int UNVACC_MAX_DAYS = 14;
    
    // for Low Risk Individuals
    public Protocol next(Person person, Test test, int numOfDays) {
        if (numOfDays <= ISOLATION_PERIOD) {
            return this;
        }

        if ((person.isVaccinated() && numOfDays > VACC_MAX_DAYS) ||
                (!person.isVaccinated() && numOfDays > UNVACC_MAX_DAYS) || (!test.isPositive())) {
            return new ProtocolDischarge2();
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return "P2";
    }
}
