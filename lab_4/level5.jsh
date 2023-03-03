public void findBestBooking(Request request, List<Driver> drivers) {

    ImList<ServiceBooking> serviceBookings = new ImList<ServiceBooking>();

    for (int i = 0; i < drivers.size(); i++) {
        for (int j = 0 ; j < drivers.get(i).getServices().size(); j++) {
            serviceBookings = serviceBookings.add(
                new ServiceBooking(drivers.get(i), request, drivers.get(i).getServices().get(j)));
        }
    }

    while (serviceBookings.size() > 0) {
        ServiceBooking cheapest = serviceBookings.get(0);
        for (int i = 0; i < serviceBookings.size(); i++) {
            cheapest = cheapest.compareTo(serviceBookings.get(i));
        }
        System.out.println(cheapest);
        serviceBookings =   serviceBookings.remove(serviceBookings.indexOf(cheapest));
    }
}
