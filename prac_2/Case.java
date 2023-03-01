class Case {
    private final Person person;
    private final ImList<Test> tests;
    private final Protocol protocol;
    private final ImList<Case> lineage;

    Case(Person person, PCR test) {
        this.person = person;
        this.tests = new ImList<Test>().add(test);
        this.protocol = getProtocol(person, test);
        this.lineage = new ImList<Case>().add(this);
    }

    Case(Person person, Test test, Protocol protocol, ImList<Case> lineage) {
        // calling the below constructor
        this(person, new ImList<Test>().add(test), protocol, lineage);
    }

    Case(Person person, ImList<Test> tests, Protocol protocol, ImList<Case> lineage) {
        this.person = person;
        this.tests = tests;
        this.protocol = protocol;
        this.lineage = lineage.add(this);
    }

    // static factory method which can be called even without object being constructed
    static Protocol getProtocol(Person person, Test test) {
        if (!test.isPositive()) {
            return new ProtocolDischarge1();
        }
        if (person.isHighRisk()) {
            return new P1();
        }
        return new P2();
    }

    public Protocol getCurrentProtocol() {
        return this.protocol;
    }
    
    public ImList<Test> getTestHistory() {
        return this.tests;
    }

    public Case test(Test test) {
        if (!test.isValid()) {
            return this;
        }

        // make a new lineage without this as it will be added back later in constructor
        ImList<Case> priorLineage = new ImList<Case>();
        for (int i = 0; i < this.lineage.size() - 1; i++) {
            priorLineage = priorLineage.add(this.lineage.get(i));
        }

        ImList<Test> updatedTests = this.tests.add(test);
        int numOfDays = updatedTests.size();
        Protocol nextProtocol = this.protocol.next(this.person, test, numOfDays);
        return new Case(this.person, updatedTests, nextProtocol, priorLineage);
    }

    public ImList<Case> getLineage() {
        return this.lineage;
    }
    
    @Override
    public String toString() {
        return this.person + " " + this.tests + " " + this.protocol;
    }
}
