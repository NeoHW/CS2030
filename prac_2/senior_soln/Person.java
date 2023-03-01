class Person {
    private final String id;
    private final String vacStatus;
    private final int healthRiskScore;
    private static final int HIGH_RISK_SCORE = 8;

    Person(String id, String vacStatus, int healthRiskScore) {
        this.id = id;
        this.vacStatus = vacStatus;
        this.healthRiskScore = healthRiskScore;
    }

    public boolean isHighRisk() {
        return healthRiskScore >= HIGH_RISK_SCORE;
    }

    public boolean isVaccinated() {
        return vacStatus.length() >= 2;
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s", this.id, this.vacStatus, isHighRisk() ? "HIGH" : "LOW");
    }

 
}
