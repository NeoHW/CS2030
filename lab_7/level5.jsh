import java.util.function.Function;

Log<Integer> sum(int n) {
    if (n == 0) {
        String output = "hit base case!";
        return Log.<Integer>of(0, output);
    } else {
        //Function<Integer, Log<Integer>> func = x -> Log.<Integer>of(x + n, "adding " + n); 

        Log<Integer> ans = sum(n - 1)
            .flatMap(x -> Log.<Integer>of(x + n, "adding " + n));
        return ans;
    }
}
