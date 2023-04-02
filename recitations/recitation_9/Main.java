public class Main {
    
    long evaluate(Compute<Long> compute) {
        while (compute.isRecursive()) {
            compute = compute.recurse();
        }
        return compute.evaluate();
    }

    Compute<Long> sum(long n, long s) {
        if (n == 0) {
            return new Base<Long>(() -> s);
        } else {
            return new Recursive<Long>(() -> sum(n - 1, n + s));
        }
    }

    long sum(long n) {
        return evaluate(sum(n, 0));
    }

    Compute<Long> factorial(long n, long s) {
        if (n == 0) {
            return new Base<Long>(() -> s);
        } else {
            return new Recursive<Long>(() -> factorial(n - 1, n * s));
        }
    }

    long factorial(long n) {
        return evaluate(factorial(n, 1));
    }
}
