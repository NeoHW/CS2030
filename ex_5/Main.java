import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.List;

class Main {

    // task 1
    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(0,n)
            .filter(x -> isPrime(x) && (isPrime(x + 2) || isPrime(x - 2)));
    }
    
    static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2,n)
            .noneMatch(x -> n % x == 0);
    }

    // task 2
    static String reverse(String str) {
        return Stream.<String>of(str.split(""))
            .reduce("", (x, y) -> y + x);
    }

    // task 3
    static long countRepeats(List<Integer> list) {
        return list.stream()
            .filter((x,y) -> x == y)
            .distinct()
            .peek(e -> System.out.println(e))
            .count();
    }


}
