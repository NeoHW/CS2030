import java.util.Optional;

class Log<T> {
    private final T value;
    private final String log;

    private Log(T value) {
        this.value = value;
        this.log = "";
    }

    private Log(T value, String log) {
        this.value = value;
        this.log = log;
    }
    
    static <T> Log<T> of(T t) {
        return new Log<T>(t);
    }

    static <U> Log<U> of(U value, String log) {
        return new Log<U>(value, log);
    }

    @Override
    public String toString() {
        return String.format("Log[%s]\n%s", this.value, this.log);
    }

}
