import java.util.function.Supplier;
import java.util.Optional;

class Lazy<T> implements Supplier<T> {
    private final Supplier<T> supplier;
    private Optional<T> cache;

    private Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
        this.cache = Optional.empty();
    }

    static <T> Lazy<T> of(Supplier<T> supplier) {
        return new Lazy<T>(supplier);
    }

    @Override
    public T get() {
        if (cache.isEmpty()) {
            cache = Optional.<T>ofNullable(supplier.get());
        }
        return cache.get();
    }
}
