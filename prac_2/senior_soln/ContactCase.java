import java.util.List;

class ContactCase extends Case {
    ContactCase(Person person, Test test, Case sourceOfContact) {
        super(person, test.isPositive() ? Case.getProtocol(person, test) : new P3(),
            test, sourceOfContact.getLineage());
    }
}

