ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> activeQueue = new ImList<Service>();
    ImList<Service> expiredQueue = new ImList<Service>();
    ImList<Service> serviceSchedule =  new ImList<Service>();
    int numOfActiveService = 0;
    
    for(int i = 0; i < cruises.size(); i++) {
        Cruise currCruise = cruises.get(i); 
        int arrivalTime = currCruise.getArrivalTime();
        int numLoaders = currCruise.getNumOfLoadersRequired();

        for(Service s : activeQueue) {
            if(s.completedService(arrivalTime)) {
                activeQueue = activeQueue.remove(activeQueue.indexOf(s));
                expiredQueue = expiredQueue.add(
                    new Service(new Loader(s.getLoaderNum()), currCruise));
            }
        }
        
        while(numLoaders > expiredQueue.size()) {
            expiredQueue = expiredQueue.add(
                new Service(new Loader(numOfActiveService + 1), currCruise));
            numOfActiveService++;
        }

        int dockersLoaded = 0;
        for(Service s: expiredQueue) {
            if(dockersLoaded == numLoaders) {
                break;
            } else {
                activeQueue = activeQueue.add(
                    new Service(new Loader(s.getLoaderNum()), currCruise));
                expiredQueue = expiredQueue.remove(expiredQueue.indexOf(s));
                serviceSchedule = serviceSchedule.add(
                    new Service(new Loader(s.getLoaderNum()), currCruise));
                dockersLoaded++;
            }
        }
    }
    return serviceSchedule;
}
