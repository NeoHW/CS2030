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

    T getValue() {
        return this.value;
    }

    String getLog() {
        return this.log.orElse("");
    }


    // level 2
    <R> Log<R> map(Function<? super T, ? extends R> mapper) {
        return new Log<R>(mapper.apply(value), log);
    }

    // level 3
    // cannot use.get()
    <R> Log<R> flatMap(Function<? super T, ? extends Log<? extends R>> mapper) {
        Log<? extends R> temp = mapper.apply(this.value);
        if (this.getLog().equals("")) {
            return Log.of(temp.getValue(), temp.getLog());
        } else if (temp.getLog().equals("")) {
            return Log.of(temp.getValue(), this.getLog());
        } else {
            return Log.of(temp.getValue(), this.getLog() + "\n" + temp.getLog());
        }
    }

    // level 4
    @Override
    public boolean equals(Object obj) { 
        if (this == obj) {
            return true;
        } else if (obj instanceof Log<?> other) {
            // note: use .equals() even for T , as int can use .equals for int also
            return (this.getValue().equals(other.getValue())) &&
                (this.getLog().equals(other.getLog()));
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        /**
        return (this.log.equals("")) 
            ? this.log.map(l -> "Log[" + value + "]").orElse("") 
            : this.log.map(l -> "Log[" + value + "]\n" + l).orElse("");
        **/
        return log.map(l -> "Log[" + value + "]\n" + l).orElse("log[" + value + "]");
    }
}
