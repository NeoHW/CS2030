import java.util.Comparator;

class TaskComp implements Comparator<Reminder>{
    public int compare(Reminder x, Reminder y) {
        return (x.getDay() == y.getDay()) ? x.getStartTime() - y.getStartTime() : x.getDay() - y.getDay();
    }
}
