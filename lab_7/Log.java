import java.util.Optional;
import java.util.function.Function;
import java.lang.IllegalArgumentException;

class Log<T> {
    private final T value;
    private final Optional<String> log;

    private Log(T value, Optional<String> log) {
        this.value = value;
        this.log = log;
    }
    
    static <T> Log<T> of(T t) {
        return Log.of(t, "");
    }

    static <T> Log<T> of(T value, String log) {
        return Optional.ofNullable(log)
            .flatMap(l -> Optional.ofNullable(value)
                .filter(x -> !(x instanceof Log))
                .map(x -> new Log<T>(x, Optional.ofNullable(l)))
            )
            .orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }

    // level 2
    <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return new Log<R>(mapper.apply(value), log);
    }

    // level 3
    // flatMap would flattens all the logs into one log?
    // <R> Log<R> flatMap(Function <? super T, 

    @Override
    public String toString() {
        return log.map(l -> "Log[" + value + "]\n" + l).orElse("Log[" + value + "]");
    }
}
