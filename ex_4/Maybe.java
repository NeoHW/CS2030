import java.util.function.Function;

class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    static <U> Maybe<U> of(U value) {
        return new Maybe<U>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    private T get() {
        return this.value;
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private boolean isPresent() {
        return !this.isEmpty();
    }

    <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
        if (this.isEmpty()) {
            return Maybe.<R>empty();
        } else {
            return Maybe.<R>of(mapper.apply(this.value));
        }
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.value + "]";
        }
    }
}
