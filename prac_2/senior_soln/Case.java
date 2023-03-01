import java.util.List;

class Case {
    private final Person person;
    private final Protocol protocol;
    private final ImList<Test> testHistory;
    private final ImList<Case> lineage;

    Case(Person person, PCR test) {
        this(person, getProtocol(person, test), new ImList<>(List.of(test)), new ImList<>());
    }

    Case(Person person, Protocol protocol, Test test, ImList<Case> priorLineage) {
        this(person, protocol, new ImList<>(List.of(test)), priorLineage);
    }

    Case(Person person, Protocol protocol, ImList<Test> testHistory, ImList<Case> priorLineage) {
        this.person = person; 
        this.protocol = protocol;
        this.testHistory = testHistory;
        this.lineage = priorLineage.add(this);
    }

    static Protocol getProtocol(Person person, Test test) {
        if (!test.isPositive()) {
            return new Discharged(DischargedStatus.FOLLOW_UP);
        }
        if (person.isHighRisk()) {
            return new P1();
        }
        return new P2();
    }

    int getNumOfDays() {
        return testHistory.size() - 1;
    }

    Protocol getCurrentProtocol() {
        return this.protocol;
    }

    ImList<Test> getTestHistory() {
        return this.testHistory;
    }

    ImList<Case> getLineage() {
        return this.lineage;
    }

    Case test(Test test) {
        if (!test.isValid()) {
            return this;
        } 

        ImList<Case> priorLineage = new ImList<>();

        for (int i = 0; i < lineage.size() - 1; i++) {
            priorLineage = priorLineage.add(lineage.get(i));
        }

        return new Case(person, protocol.next(person, test, getNumOfDays() + 1), 
                testHistory.add(test), priorLineage);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", person, testHistory, protocol);
    }
}
