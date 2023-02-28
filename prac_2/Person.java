class Person {
    private final String id;
    private final String vaccStatus;
    private final String riskStatus;
    private static final int HIGH_RISK_SCORE = 8;

    Person(String id, String vaccStatus, int riskScore) {
        this.id = id;
        this.vaccStatus = vaccStatus;
        if (riskScore >= HIGH_RISK_SCORE) {
            this.riskStatus = "HIGH";
        } else {
            this.riskStatus = "LOW";
        }
    }

    public boolean isVaccinated() {
        return this.vaccStatus.length() >= 2;
    }

    public boolean isHighRisk() {
        return this.riskStatus.equals("HIGH");
    }

    public String toString() {
        return this.id + "/" + this.vaccStatus + "/" + this.riskStatus;
    }

}
