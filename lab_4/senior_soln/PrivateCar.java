import java.util.List;

class PrivateCar extends Driver {
    private static final ImList<Service> services = 
        new ImList<>(List.of(new JustRide(), new ShareARide()));

    PrivateCar(String license, int waitingTime) {
        super(license, waitingTime, services);
    }

    @Override
    public String toString() {
        return super.toString() + " PrivateCar";
    }
}