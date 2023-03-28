import java.util.function.Function;

Log<Integer> sum(int n) {
    if (n == 0) {
        String output = "hit base case!";
        return Log.<Integer>of(0, output);
    } else {
        // Function<Integer, Log<Integer>> func = x -> Log.<Integer>of(x + n, "adding " + n); 
        Log<Integer> ans = sum(n - 1)
            .flatMap(x -> Log.<Integer>of(x + n, "adding " + n));
        return ans;
    }
}

// iterative method
Log<Integer> f (int n) {
    Log<Integer> logger = Log.<Integer>of(n);
    while (n != 1) {
        if (n % 2 == 0) {
            logger = logger
                .flatMap(x -> Log.<Integer>of(x / 2, "" + x + " / " + 2));
            n /= 2;
        } else {
            // need to declare final int tempN as lambda can only use final values
            final int tempN = n;
            logger = logger
                .flatMap(x -> Log.<Integer>of(3 * x + 1, "" + 3 + "(" + tempN + ")" + " + " + 1));
            n = 3 * n + 1;
        }
    }
    logger = logger.flatMap(x -> Log.<Integer>of(x , "1"));
    return logger;
}

// recursive method
// might need overloaded function?
