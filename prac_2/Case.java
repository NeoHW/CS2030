class Case {
    private final Person person;
    private final ImList<Test> tests;
    private final Protocol protocol;

    Case(Person person, PCR test) {
        this.person = person;
        this.tests = new ImList<Test>().add(test);
        if (test.isPositive()) {
            this.protocol = new P1();
        } else {
            this.protocol = new ProtocolDischarge();
        }
    }

    Case(Person person, ImList<Test> tests, Protocol protocol) {
        this.person = person;
        this.tests = tests;
        this.protocol = protocol;
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
        Protocol nextProtocol = this.protocol.next(this.person, test);
        return new Case(this.person, this.tests.add(test), nextProtocol);
    }
    
    @Override
    public String toString() {
        return this.person + " " + this.tests + " " + this.protocol;
    }
}
