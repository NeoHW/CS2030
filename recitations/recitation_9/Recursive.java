import java.util.function.Supplier;

class Recursive<T> implements Compute<T> {
    private final Supplier<Compute<T>> supplier;

    Recursive(Supplier<Compute<T>> supplier) {
        this.supplier = supplier;
    }
    
    public boolean isRecursive() {
        return true;
    }

    public Compute<T> recurse() {
        return this.supplier.get();
    }

    public T evaluate() {
        throw new IllegalStateException("Evaluating a recursive case");
    }
}
