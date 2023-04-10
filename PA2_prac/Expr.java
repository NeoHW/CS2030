class Expr<T> {
    private final T value;

    private Expr(T value) {
        this.value = value;
    }

    static <T> Expr<T> of(T value) {
        return new Expr<T>(value);
    }

    Expr<T> op(Operator<T> operator, T num) {
        return Expr.<T>of(operator.apply(this.value, num));
    }

    @Override
    public String toString() {
        return String.format("(%s)", this.value);
    }

}
