import java.util.Comparator;

class EventComp implements Comparator<Event> {
    
    // returns <0 if x comes first, >0 if y comes first
    public int compare(Event x, Event y) {
        if (x.getTime() == y.getTime()) {
            return x.getCustomer().getCustomerNumber() - y.getCustomer().getCustomerNumber();
        } else {
            if (x.getTime() < y.getTime()) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
