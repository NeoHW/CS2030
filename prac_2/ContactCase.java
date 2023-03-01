class ContactCase extends Case {

    ContactCase(Person person, Test test, Case sourceOfContact) {
        super(person, test, test.isPositive() ? Case.getProtocol(person, test) : new P3(),
                sourceOfContact.getLineage());
    }

}
