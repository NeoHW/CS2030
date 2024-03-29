import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

class AbstractNum<T> {
    protected static final Function<Integer, Integer> s = x -> x + 1; // s adds one
    protected static final Function<Integer, Integer> n = x -> -x; // n changes the sign
    protected static final Predicate<Integer> valid = x -> x >= 0; // valid checks if num >= 0

    protected final Optional<T> opt;

    protected AbstractNum(T i) {
        this(Optional.<T>of(i));
    }

    protected AbstractNum(Optional<T> opt) {
        this.opt = opt;
    }

    static AbstractNum<Integer> zero() {
        return new AbstractNum<Integer>(0);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else {
            if (obj instanceof AbstractNum<?> abs) {
                return abs.opt.equals(this.opt);
            } else {
                return false;
            }
        }
    }

    // isValid() checks if Optional<T> opt contains a value or not!!
    protected boolean isValid() {
        return this.opt.map(x -> true).orElse(false);
    }

    public String toString() {
        return this.opt.map(x -> x.toString()).orElse("NaN");
    }
}
