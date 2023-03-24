import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;

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

    // level 1
    public <U> boolean equals(Maybe<U> other) {
        if (!(other instanceof Maybe)) { 
            return false;
        }
        if ((this.isEmpty() && other.isEmpty())) {
            return true;
        }
        if ((this.isPresent() && other.isPresent()) && this.value.equals(other.get())) {
            return true;
        }
        return false;
    }

    // level 2 
    public Maybe<T> filter(Predicate<? super T> p) {
        if (this.isEmpty() || p.test(this.value) ==  false) {
            return Maybe.<T>empty();
        } else {
            return this;
        }
    }

    // level 3
    public void ifPresent(Consumer<? super T> c) {
        if (this.isPresent()) {
            c.accept(this.value);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> c, Runnable r) {
        if (this.isPresent()) {
            c.accept(this.value);
        } else {
            r.run();
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
