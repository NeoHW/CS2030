import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

class DnC<T,R> {
    private final Supplier<Optional<T>> problem;
    private final Predicate<T> pred; // returns true if atomic
    private final Function<T,R> soln; // only applies for atomic problems
    private final Optional<Function<T,Pair<T,T>>> breakdown;

    protected DnC(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln) {
        this.problem = () -> Optional.of(prob.get());
        this.pred = pred;
        this.soln = soln;
        this.breakdown = Optional.empty();
    }

    /*
     protected DnC(T prob, Predicate<T> pred, Function<T,R> soln, Function<T, Pair<T,T>> breakdown) {
        this.problem = () -> Optional.<T>of(prob);
        this.pred = pred;
        this.soln = soln;
        this.breakdown = Optional.<Function<T, Pair<T,T>>>of(breakdown);
    }
     */

    protected DnC(Optional<T> prob, Predicate<T> pred, Function<T,R> soln, Optional<Function<T,Pair<T,T>>> breakdown) {
        this.problem = () -> prob;
        this.pred = pred;
        this.soln = soln;
        this.breakdown = breakdown;
    }

    static <T,R> DnC<T,R> of(T prob, Predicate<T> pred, Function<T,R> soln) {
        return new DnC<T,R>(() -> prob, pred, soln);
    }

    // level 3 overloaded of operator
    static <T,R> DnC<T,R> of(T prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown) {
        return new DnC<T,R>(Optional.of(prob), pred, soln, Optional.of(breakdown));
    }

    // level 5 overloaded of operator
    /* 
    static <T,R> DnC<T,R> of(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown) {
        return new DnC<T,R>(prob, pred, soln, breakdown);
    }
    */

    // this will be like toString()
    void peek(Consumer<T> action) {
        problem.get().ifPresent(action);
    }

    Optional<R> solve() {
        return problem.get().filter(pred).map(soln);
    }

    DnC<T,R> left() {
        return problem.get()
            .filter(Predicate.not(pred))
            .flatMap(x -> 
            breakdown.map(div -> {
                // Pair<T,T> pair = div.apply(problem);
                // return new DnC<T,R>(Optional.of(pair.first()), pred, soln, breakdown);
                return new DnC<T,R>(Optional.of(div.apply(x).first()), pred, soln, breakdown);
            })
        ).orElse(this);
    }
 
    DnC<T,R> right() {
        return problem.get()
            .filter(Predicate.not(pred))
            .flatMap(x -> 
            breakdown.map(div -> {
                // Pair<T,T> pair = div.apply(problem);
                // return new DnC<T,R>(Optional.of(pair.first()), pred, soln, breakdown);
                return new DnC<T,R>(Optional.of(div.apply(x).second()), pred, soln, breakdown);
            })
        ).orElse(this);
    }

    /*
    // level 4
    Optional<R> solve(BinaryOperator<R> op) {
        return problem.filter(pred).map(soln);
    }
    */
}
