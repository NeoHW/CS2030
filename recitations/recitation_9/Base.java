import java.util.function.Supplier;

class Base<T> implements Compute<T> {

    private final Supplier<T> supplier;

    Base(Supplier<T> supplier) {
        this.supplier = supplier;
    }
    
    public boolean isRecursive() {
        return false;
    }

    public Compute<T> recurse() {
        throw new IllegalStateException();
    }

    public T evaluate() {
        return this.supplier.get();
    }    
}
