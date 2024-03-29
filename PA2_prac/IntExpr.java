class IntExpr extends AbstractIntExpr {
    private static final Operator<Integer> subtraction =
        Operator.<Integer>of((x, y) -> x - y, 4);
    private static final Operator<Integer> division  =
        Operator.<Integer>of((x, y) -> x / y, 3);
    // private static final Operator<Integer> exponentiation  =
        // Operator.of((x, y) -> IntStream.range(0, y).reduce(1, (m,n) -> m * x), 2);

    // loop implementation for exponential
    private static final Operator<Integer> exp =
        Operator.of((x,y) -> {
            int temp = 1;
            for (int i = 0; i < y; i++) {
                temp = temp * x;
            }
            return temp;
        }, 2);


    IntExpr(Expr<Integer> expr) {
        super(expr);
    } 

    static IntExpr of(int value) {
        return new IntExpr(Expr.<Integer>of(value));
    }

    IntExpr add(int value) {
        return new IntExpr(super.op(addition, value));
    }

    IntExpr mul(int value) {
        return new IntExpr(super.op(multiplication, value));
    }

    IntExpr sub(int value) {
        return new IntExpr(super.op(subtraction, value));
    }

    IntExpr div(int value) {
        return new IntExpr(super.op(division, value));
    }

    IntExpr exp(int value) {
        return new IntExpr(super.op(exp, value));
    }

    // level 5
    IntExpr add(IntExpr x) {
        return new IntExpr(super.op(addition, x));
    }

    IntExpr mul(IntExpr x) {
        return new IntExpr(super.op(multiplication, x));
    }

    IntExpr sub(IntExpr x) {
        return new IntExpr(super.op(subtraction, x));
    }

    IntExpr div(IntExpr x) {
        return new IntExpr(super.op(division, x));
    }

    IntExpr exp(IntExpr x) {
        return new IntExpr(super.op(exp, x));
    }
    
    @Override
    public String toString() {
        return String.format("(%s)", super.evaluate());
    }
}
