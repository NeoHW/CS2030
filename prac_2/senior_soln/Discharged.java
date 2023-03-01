class Discharged implements Protocol {

    private final DischargedStatus dischargedStatus;

    Discharged(DischargedStatus dischargedStatus) {
        this.dischargedStatus = dischargedStatus;
    }

    @Override
    public Protocol next(Person person, Test test, int numOfDays) {
        if (test.isPositive()) {
            return new Discharged(DischargedStatus.MEDICAL_ATTN);
        }
        return this;
    }

    @Override
    public String toString() {
        return String.format("Discharged: %s", this.dischargedStatus);
    }
}
