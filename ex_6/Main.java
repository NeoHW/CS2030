import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.List;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: cs2030 (orig. Ooi Wei Tsang)
 */
class Main {

    static CompletableFuture<BusRoutes> processQuery(String query) {
        Scanner sc = new Scanner(query);
        BusStop srcBusStop = new BusStop(Integer.valueOf(sc.next()).toString());
        String searchString = sc.next();
        sc.close();
        return BusSg.findBusServicesBetween(srcBusStop, searchString);
    }

    public static void main(String[] args) {
        /** 
        CompletableFuture<Void> cf1 = BusAPI.getBusStopsServedBy("95")
            .thenAccept(x -> System.out.println(x));
        CompletableFuture<Void> cf2 = BusAPI.getBusStopsServedBy("96")
            .thenAccept(x -> System.out.println(x));
        cf1.join();
        cf2.join();
        */

        Instant start = Instant.now();
        Scanner sc = new Scanner(System.in);
        String tempResult = "";


        List<CompletableFuture<BusRoutes>> busRoutes = sc.useDelimiter("\n").tokens()
            .map(s -> processQuery(s))
            .toList();
        
        CompletableFuture<String> cfResult = CompletableFuture.completedFuture(tempResult);
        
        for (CompletableFuture<BusRoutes> cfbr : busRoutes) {
            CompletableFuture<String> cfbrOutput = cfbr.thenCompose(x -> x.description());
            cfResult = cfResult.thenCombine(cfbrOutput, (x, y) -> x + "\n" + y);
        }

        System.out.println(cfResult.join());
        sc.close();
        Instant stop = Instant.now();
        System.out.printf("Took %,dms\n", Duration.between(start, stop).toMillis());
    }
    
}
