import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.BinaryOperator;

class DnC<T,R> {
    private final T problem;
    private final Predicate<T> pred; // returns true if atomic
    private final Function<T,R> soln; // only applies for atomic problems
    private final Optional<Function<T,Pair<T,T>>> breakdown;

    protected DnC(T prob, Predicate<T> pred, Function<T,R> soln) {
        this.problem = prob;
        this.pred = pred;
        this.soln = soln;
        this.breakdown = Optional.empty();
    }

    protected DnC(T prob, Predicate<T> pred, Function<T,R> soln, Optional<Function<T,Pair<T,T>>> breakdown) {
        this.problem = prob;
        this.pred = pred;
        this.soln = soln;
        this.breakdown = breakdown;
    }

    static <T,R> DnC<T,R> of(T prob, Predicate<T> pred, Function<T,R> soln) {
        return new DnC<T,R>(prob, pred, soln);
    }

    // level 3 overloaded of operator
    static <T,R> DnC<T,R> of(T prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown) {
        return new DnC<T,R>(prob, pred, soln, Optional.of(breakdown));
    }

    // level 5 overloaded of operator
    /* 
    static <T,R> DnC<T,R> of(Supplier<T> prob, Predicate<T> pred, Function<T,R> soln, Function<T,Pair<T,T>> breakdown) {
        return new DnC<T,R>(prob, pred, soln, breakdown);
    }
    */

    // this will be like toString()
    void peek(Consumer<T> action) {
        action.accept(this.problem);
    }

    Optional<R> solve() {
        return Optional.of(problem).filter(pred).map(soln);
    }

    DnC<T,R> left() {
        return Optional.of(problem)
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
        return Optional.of(problem)
            .filter(Predicate.not(pred))
            .flatMap(x -> 
            breakdown.map(div -> {
                return new DnC<T,R>(div.apply(x).second(), pred, soln, breakdown);
            })
        ).orElse(this);
    }

    // level 4
    Optional<R> solve(BinaryOperator<R> op) {
        return this.solve().or(
            () -> left().solve(op)
            .flatMap(lAns -> right().solve(op)
            .map(rAns -> op.apply(lAns, rAns)))
        );
    }
}
