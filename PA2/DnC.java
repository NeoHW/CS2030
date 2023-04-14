import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;

class DnC<T,R> {
    private final Supplier<Optional<T>> problem;
    private final Predicate<T> pred;
    private final Function<T,R> soln;
    private final Optional<Function<T,Pair<T,T>>> breakdown;

    protected DnC(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln) {
        this.problem = prob.get().map(x -> (() -> Optional.<T>of(x)));
        this.pred = pred;
        this.soln = soln;
        this.breakdown = Optional.empty();
    }

     protected DnC(T prob, Predicate<T> pred, Function<T,R> soln, Function<T, Pair<T,T>> breakdown) {
        this.problem = () -> Optional.<T>of(prob);
        this.pred = pred;
        this.soln = soln;
        this.breakdown = Optional.<Function<T, Pair<T,T>>>of(breakdown);
    }

    static <T,R> DnC<T,R> of(T prob, Predicate<T> pred, Function<T,R> soln) {
        return new DnC<T,R>(prob, pred, soln);
    }

    // level 3 overloaded of operator
    static <T,R> DnC<T,R> of(T prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown) {
        return new DnC<T,R>(prob, pred, soln, breakdown);
    }

    // level 5 overloaded of operator
    static <T,R> DnC<T,R> of(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown) {
        return new DnC<T,R>(prob, pred, soln, breakdown);
    }

    // this will be like toString()
    void peek(Consumer<T> action) {
        problem.get().ifPresent(action);
    }

    Optional<R> solve() {
        return problem.get().filter(pred).map(soln);
    }

    DnC<T,R> left() {
        return DnC.<T,R>of(problem.map(breakdown.get()).get().first()
                .orElse(problem)
                , pred, soln, breakdown);
    }

    DnC<T,R> right() {
        return DnC.<T,R>of(problem.map(breakdown.get()).second()
                .orElse(problem)
                , pred, soln, breakdown);
    }

    // level 4
    Optional<R> solve(BinaryOperator<R>) {
        return problem.filter(pred).map(soln);
    }

}
