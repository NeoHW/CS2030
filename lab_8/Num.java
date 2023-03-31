import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.IntStream;

class Num extends AbstractNum<Integer> {
    
    // only constructors can call constructors
    private Num(int i) {
        super(i);
    }

    private Num(Optional<Integer> opt) {
        super(opt);
    }

    private Num(AbstractNum<Integer> absNum) {
        super(absNum.opt);
    }

    // of method is NOT a constructor, must use new keyword
    static Num of(int i) {
        if (valid.test(i)) {
            return new Num(i);
        } else {
            return new Num(Optional.<Integer>empty());
        }
    }

    static Num zero() {
        return new Num(AbstractNum.zero());
    }

    static Num one() {
        return new Num(AbstractNum.zero().opt.map(x -> s.apply(x)));
    }

    Num succ() {
        return new Num(this.opt.map(x -> s.apply(x)));
    }

    Num add(Num other) {
        Num initial = Num.zero();
        Num ans = this;
        while (!initial.equals(other)) {
            ans = ans.succ();
            initial = initial.succ();
        }
        return ans;
    }

}
