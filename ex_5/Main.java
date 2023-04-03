import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.function.UnaryOperator;

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
        // general logic : check if in front num is same and behind num is diff
        // and take into account when last num is same as 2nd last num
        return IntStream.rangeClosed(1, list.size() - 1)
            .filter(x -> (list.get(x) == list.get(x - 1)) && 
                (x == list.size() - 1 || 
                list.get(x) != list.get(x + 1)))
            .count();
    }

    // task 4
    static UnaryOperator<List<Integer>> generateRule() {
        return new UnaryOperator<List<Integer>>() {
            public List<Integer> apply(List<Integer> list) {
                return list.stream()
                    .mapToInt(x -> x)
                    .map(x -> {
                        if (x == 1) {
                            return 0;
                        } else {
                            return () ? 1 : 0;
                        }
                    })
                    .toList();
            }
        };
    }

    static Stream<String> gameOfLife(List<Integer> list, UnaryOperator<List<Integer>> rule, int n) {

    }
}
