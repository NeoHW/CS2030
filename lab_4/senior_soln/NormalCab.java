import java.util.List;

class NormalCab extends Driver {
    private static final ImList<Service> services = 
        new ImList<>(List.of(new JustRide(), new TakeACab()));

    NormalCab(String license, int waitingTime) {
        super(license, waitingTime, services);
    }

    @Override
    public String toString() {
        return super.toString() + " NormalCab";
    }
}