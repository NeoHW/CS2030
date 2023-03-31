import java.util.Optional;

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

}
