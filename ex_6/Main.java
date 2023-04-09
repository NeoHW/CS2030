import java.time.Instant;
import java.time.Duration;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

/**
 * This program finds different ways one can travel by bus (with a bit 
 * of walking) from one bus stop to another.
 *
 * @author: cs2030 (orig. Ooi Wei Tsang)
 */
class Main {

    public static void main(String[] args) {
        CompletableFuture<Void> cf1 = BusAPI.getBusStopsServedBy("95")
            .thenAccept(x -> System.out.println(x));
        CompletableFuture<Void> cf2 = BusAPI.getBusStopsServedBy("96")
            .thenAccept(x -> System.out.println(x));
        cf1.join();
        cf2.join();
    }
}
