import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.BinaryOperator;

class DnC<T,R> {
    private final Supplier<T> problem;
    private final Predicate<T> pred; // returns true if atomic
    private final Function<T,R> soln; // only applies for atomic problems
    private final Optional<Function<T, Pair<Supplier<T>, Supplier<T>>>> breakdown;

    protected DnC(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln) {
        this.problem = prob;
        this.pred = pred;
        this.soln = soln;
        this.breakdown = Optional.empty();
    }

    protected DnC(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln, Optional<Function<T, Pair<Supplier<T>, Supplier<T>>>> breakdown) {
        this.problem = prob;
        this.pred = pred;
        this.soln = soln;
        this.breakdown = breakdown;
    }

    static <T,R> DnC<T,R> of(T prob, Predicate<T> pred, Function<T,R> soln) {
        return new DnC<T,R>(() -> prob, pred, soln);
    }

    // level 3 overloaded of operator
    static <T,R> DnC<T,R> of(T prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown) {
        return new DnC<T,R>(() -> prob, pred, soln, Optional.of(breakdown.andThen(pair -> Pair.of(() -> pair.first(), () -> pair.second()))));
    }

    static <T,R> DnC<T,R> of(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown, Function<T, Pair<Supplier<T>, Supplier<T>>> divider) {
        return new DnC<T,R>(prob, pred, soln, Optional.of(divider));
    }

    // level 5 overloaded of operator
    /* 
    static <T,R> DnC<T,R> of(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown) {
        return new DnC<T,R>(prob, pred, soln, breakdown);
    }
    */

    // this will be like toString()
    void peek(Consumer<T> action) {
        Optional.of(problem.get()).ifPresent(action);
    }

    Optional<R> solve() {
        return this.solve(this.problem.get());
    }

    Optional<R> solve(T prob) {
        return Optional.of(prob).filter(pred).map(soln);
    }

    DnC<T,R> left() {
        return this.left(problem.get());
    }

    DnC<T,R> left(T prob) {
        return Optional.of(prob)
            .filter(Predicate.not(pred))
            .flatMap(x -> 
            breakdown.map(div -> {
                // Pair<T,T> pair = div.apply(problem);
                // return new DnC<T,R>(Optional.of(pair.first()), pred, soln, breakdown);
                return new DnC<T,R>(div.apply(x).first(), pred, soln, breakdown);
            })
        ).orElse(this);
    }

    DnC<T,R> right() {
        return this.right(problem.get());
    }

    DnC<T,R> right(T prob) {
        return Optional.of(prob)
            .filter(Predicate.not(pred))
            .flatMap(x -> 
            breakdown.map(div -> {
                return new DnC<T,R>(div.apply(x).second(), pred, soln, breakdown);
            })
        ).orElse(this);
    }

    // level 4
    Optional<R> solve(T prob, BinaryOperator<R> op) {
        return this.solve(prob).or(
            () -> left(prob).solve(op)
            .flatMap(lAns -> right(prob).solve(op)
            .map(rAns -> op.apply(lAns, rAns)))
        );
    }

    Optional<R> solve(BinaryOperator<R> op) {
        return this.solve(this.problem.get(), op);
    }
}
