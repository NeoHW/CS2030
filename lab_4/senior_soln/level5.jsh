import java.util.List;

void findBestBooking(Request request, List<Driver> drivers) {
    ImList<Booking> bookings = new ImList<>();
    for (Driver driver : drivers) {
        for (Service service : driver.getServices()) {
            bookings = bookings.add(new Booking(driver, request, service));
        }
    }
    bookings = bookings.sort((x, y) -> x.compareTo(y));
    for (Booking booking : bookings) {
        System.out.println(booking);
    }
}