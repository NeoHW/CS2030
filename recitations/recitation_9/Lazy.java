import java.util.function.Supplier;
import java.util.function.Function;
import java.util.Optional;

class Lazy<T> implements Supplier<T> {
    private final Supplier<T> supplier;
    private Optional<T> cache;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
        this.cache = Optional.<T>empty();
    }

    static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<T>(supplier);
    }

    /**
    static <T> Lazy<T> of(T t) {
        return new Lazy<T>(() -> t);
    }
    */

    @Override
    public T get() {
        // if you use orElse, it would be too eager an evaluation 
        // foo(bar(1)) : bar(1) has to be evaluated before getting passed into foo()
        // therefore use orElseGet and pass in a supplier instead
        T v = this.cache.orElseGet(this.supplier);
        this.cache = Optional.<T>of(v);
        return v;
    }

    <R> Lazy<R> map(Function<? super T, ? extends R> mapper) {
        // as this is a supplier, .get() wont be called eagerly, but only when supplier .get() method is called
        return Lazy.<R>of(() -> mapper.apply(this.get()));
    }

    <R> Lazy<R> flatMap(Function<? super T, ? extends Lazy<? extends R>> mapper) {
        return Lazy.<R>of(() -> mapper.apply(this.get()).get());
    }

    // equals mwethod
    @Override
    public boolean equals(Object obj) { 
        if (this == obj) {
            return true;
        } else if (obj instanceof Lazy<?> other) {
            return 
        } else {
            return false;
        }
    }
}
