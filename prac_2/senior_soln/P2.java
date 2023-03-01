class P2 implements Protocol {
    private static final int ISOLATION_PERIOD = 3;
    private static final int VACCINATED_DISCHARGE_DAY = 7;
    private static final int UNVACCINATED_DISCHARGE_DAY = 14;

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (numOfDays >= ISOLATION_PERIOD && !test.isPositive()
                || (person.isVaccinated() && numOfDays >= VACCINATED_DISCHARGE_DAY)
                || (!person.isVaccinated() && numOfDays >= UNVACCINATED_DISCHARGE_DAY)) {
            return new Discharged(DischargedStatus.COMPLETE);
        }

        return this;
    }

    @Override
    public String toString() {
        return "P2";
    }
}
