import java.util.function.BinaryOperator;

class Operator<T> implements Comparable<Operator<T>> {
    // usually put the whole class into Comparable to compare that object^
    private final int precValue;
    private final BinaryOperator<T> operator;

    private Operator(BinaryOperator<T> operator, int precValue) {
        this.precValue = precValue;
        this.operator = operator;
    }

    int getPrecValue() {
        return this.precValue;
    }

    static <T> Operator<T> of(BinaryOperator<T> operator, int precValue) {
        return new Operator<T>(operator, precValue);
    }

    T apply(T first, T second) {
        return operator.apply(first, second);
    }

    @Override
    public int compareTo(Operator<T> other) {
        if (this.precValue == other.getPrecValue()) {
            return 0;
        } else {
            return this.precValue > other.getPrecValue() ? 1 : -1;
        }
    }

    @Override
    public String toString() {
        return "Operator of precedence " + this.precValue;
    }

}
