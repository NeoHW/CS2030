import java.util.Optional;
import java.util.function.BinaryOperator;

class IntExpr extends AbstractIntExpr {
    private static final double DOUBLE = 1.0;
    private static final Operator<Integer> subtraction =
        Operator.<Integer>of((x, y) -> x - y, 4);
    private static final Operator<Integer> division  =
        Operator.<Integer>of((x, y) -> x / y, 3);
    private static final Operator<Integer> exponentiation  =
        // pow need (double, double) and so typecast it back to int
        Operator.<Integer>of((x, y) -> (int) Math.pow(x * DOUBLE, y * DOUBLE), 2);

    IntExpr(Expr<Integer> expr) {
        super(expr);
    } 

    static IntExpr of(int value) {
        return new IntExpr(Expr.<Integer>of(value));
    }

    IntExpr add(int value) {
        return new IntExpr(super.op(addition, Optional.of(IntExpr.of(value))));
    }

    IntExpr mul(int value) {
        return new IntExpr(super.op(multiplication, Optional.of(IntExpr.of(value))));
    }

    IntExpr sub(int value) {
        return new IntExpr(super.op(subtraction, Optional.of(IntExpr.of(value))));
    }

    IntExpr div(int value) {
        return new IntExpr(super.op(division, Optional.of(IntExpr.of(value))));
    }

    IntExpr exp(int value) {
        return new IntExpr(super.op(exponentiation, Optional.of(IntExpr.of(value))));
    }
    
    @Override
    public String toString() {
        return String.format("(%s)", super.evaluate());
    }
}
