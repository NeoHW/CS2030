import java.util.function.Supplier;

class Recursive<T> implements Compute<T> {

    private final Supplier<? extends Recursive< ? extends T>> supplier;

    Recursive(Supplier<? extends Recursive<? extends T>> supplier) {
        this.supplier = supplier;
    }
    
    public boolean isRecursive() {
        return true;
    }

    public Compute<T> recurse() {
        return new Recursive<T>(() -> supplier.get());
    }

    public T evaluate() {
        throw new IllegalStateException();
    }
}
