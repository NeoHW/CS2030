import java.util.Optional;
import java.util.function.BinaryOperator;

class Expr<T> {
    private final T left;
    private final Optional<Operator<T>> operator;
    private final Optional<Expr<T>> right; 

    Expr(T left, Optional<Operator<T>> operator, Optional<Expr<T>> right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    Expr(Expr<T> expr) {
        this(expr.left, expr.operator, expr.right);
    }

    static <T> Expr<T> of(T t) {
        return new Expr<T>(t, Optional.empty(), Optional.empty());
    }

    // this op method will call the actual implementation
    Expr<T> op(Operator<T> operator, T t) {
        return op(operator, Optional.of(Expr.of(t)));
    }

    // return a new Expr, not a final value!
    Expr<T> op(Operator<T> oper, Optional<Expr<T>> t) {
        return this.operator.map(x -> x.compareTo(oper) <= 0 ?
                new Expr<T>(this.evaluate(), Optional.of(oper), t) :
                new Expr<T>(left, this.operator, right.map(y -> y.op(oper, t))))
                .orElse(new Expr<T>(left, Optional.of(oper), t));
    }

    T evaluate() {
        return this.operator.map(
                // can use orElseThrow() here as you know it wont come to that
                x -> x.apply(left, right.map(y -> y.evaluate()).orElseThrow()))
            .orElse(this.left);
    }

    @Override
    public String toString() {
        return String.format("(%s)", this.evaluate());
    }

}
