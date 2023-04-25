import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

interface Maybe<T> {

    static <T> Maybe<T> empty() {
        return Maybe.<T>of(null);
    }

    static <T> Maybe<T> of(T valueIn) {
        return new Maybe<T>() {
            private final T value = valueIn;

            public T get() {
                return this.value;
            }
        
            public boolean isEmpty() {
                return this.value == null;
            }
        
            public boolean isPresent() {
                return !this.isEmpty();
            }
        
            public <R> Maybe<R> map(Function<? super T, ? extends R> mapper) {
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

            // level 4
            public T orElse(T other) {
                if (this.isPresent()) {
                    return this.value;
                } else {
                    return other;
                }
            }

            public T orElseGet(Supplier<? extends T> s) {
                if (this.isPresent()) {
                    return this.value;
                } else {
                    return s.get();
                }
            }

            // why must it have <? extends Maybe<...>> 
            // why would this make Maybe<Integer> a subtype of Maybe<Object> 
            public Maybe<T> or(Supplier<? extends Maybe<? extends T>> s) {
                if (this.isPresent()) {
                    return this;
                } else {
                    Maybe<? extends T> temp = s.get();
                    return Maybe.<T>of(temp.get());
                }
            }

            // level 5
            public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper) {
                if (this.isEmpty()) {
                    return Maybe.<R>empty();
                } else {
                    // return Maybe.<R>of(mapper.apply(this.value).get());
                    // why is ^above not acceptable?
                    Maybe<? extends R> temp = mapper.apply(this.value);
                    return Maybe.<R>of(temp.get());
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
        };
    }

    // specify the contracts
    public T get();
    public boolean isEmpty();
    public boolean isPresent();
    public <R> Maybe<R> map(Function<? super T, ? extends R> mapper);
    public <U> boolean equals(Maybe<U> other);
    public Maybe<T> filter(Predicate<? super T> p);
    public void ifPresent(Consumer<? super T> c);
    public void ifPresentOrElse(Consumer<? super T> c, Runnable r);
    public T orElse(T other);
    public T orElseGet(Supplier<? extends T> s);
    public Maybe<T> or(Supplier<? extends Maybe<? extends T>> s);
    public <R> Maybe<R> flatMap(Function<? super T, ? extends Maybe<? extends R>> mapper);
    public String toString();
}
