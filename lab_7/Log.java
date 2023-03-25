import java.util.Optional;

class Log<T> {
    private final Optional<T> value;
    private final Optional<String> log;

    private Log(Optional<T> value, Optional<String> log) {
        this.value = value;
        this.log = log;
    }
    
    static <T> Log<T> of(T t) {
        return Log.of(t, null);
    }

    static <T> Log<T> of(T value, String log) {
        return new Log<T>(Optional.<T>ofNullable(value), Optional.<String>ofNullable(log));
    }

    @Override
    public String toString() {
        if (log.isEmpty()) {
            return String.format("Log[%s]", this.value.get());
        } else {
            return String.format("Log[%s]\n%s", this.value.get(), this.log.get());
        }
    }
}
