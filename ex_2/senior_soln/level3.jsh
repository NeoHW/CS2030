ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> services = new ImList<>();
    ImList<Service> active = new ImList<>();
    ImList<Service> expired = new ImList<>();

    int loaderNo = 1;
    for (Cruise cruise : cruises) {
        int numOfLoadersRequired = cruise.getNumOfLoadersRequired();
        int numOfLoadersCurrently = 0;
        ImList<Integer> toBeTransferred = new ImList<>();
        for (int i = 0; i < active.size(); i++) {
            if (active.get(i).getServiceEndTime() <= cruise.getArrivalTime()) {
                toBeTransferred = toBeTransferred.add(i);
                expired = expired.add(active.get(i));
            }
        }
        for (int i = toBeTransferred.size() - 1; i >= 0; i--) {
            active = active.remove(toBeTransferred.get(i));
        }
        while (numOfLoadersCurrently < numOfLoadersRequired) {
            if (!expired.isEmpty()) {
                Loader loader = expired.get(0).getLoader();
                expired = expired.remove(0);
                Service service = new Service(loader, cruise);
                active = active.add(service);
                services = services.add(service);
            } else {
                Loader loader = new Loader(loaderNo);
                loaderNo += 1;
                Service service = new Service(loader, cruise);
                active = active.add(service);
                services = services.add(service);
            }
            numOfLoadersCurrently += 1;
        }
    }
    return services;
}