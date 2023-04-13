import java.util.Optional;
import java.util.function.Supplier;

class Expr<T> {
    private final Supplier<T> left;
    private final Optional<Operator<T>> operator;
    private final Supplier<Optional<Expr<T>>> right; 

    Expr(Supplier<T> left, Optional<Operator<T>> operator, Supplier<Optional<Expr<T>>> right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    Expr(Expr<T> expr) {
        this(expr.left, expr.operator, expr.right);
    }

    static <T> Expr<T> of(T t) {
        return new Expr<T>(() -> t, Optional.empty(), () -> Optional.empty());
    }

    Expr<T> op(Operator<T> operator, Expr<T> t) {
        return op(operator, () -> Optional.of(Expr.of(t.evaluate())));
    }

    // this op method will call the actual implementation
    Expr<T> op(Operator<T> operator, T t) {
        return op(operator, () -> Optional.of(Expr.of(t)));
    }

    Expr<T> op(Operator<T> oper, Supplier<Optional<Expr<T>>> t) {
        return this.operator.map(x -> x.compareTo(oper) <= 0 ?
                // for level 5: need to delay evaluate() and right.map(y -> ...)
                new Expr<T>(() -> this.evaluate(),
                    Optional.of(oper),
                    t) :
                new Expr<T>(left, 
                    this.operator,
                    () -> right.get().map(y -> y.op(oper, t))))
                .orElse(new Expr<T>(left, Optional.of(oper), t));
    }

    T evaluate() {
        return this.operator.map(
                // can use orElseThrow() here as you know it wont come to that
                x -> x.apply(left.get(), right.get().map(y -> y.evaluate()).orElseThrow()))
            .orElseGet(this.left); // orElseGet() as it is a supplier
    }

    

    @Override
    public String toString() {
        return String.format("(%s)", this.evaluate());
    }

}
