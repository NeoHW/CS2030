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

    // calling succ repeatedly
    Num add(Num other) {
        if (!(this.isValid() && other.isValid())) {
            return new Num(Optional.<Integer>empty());
        }
        Num counter = Num.zero();
        Num ans = this;
        while (!counter.equals(other)) {
            ans = ans.succ();
            counter = counter.succ();
        }
        return ans;
    }

    // m.mul(n) = m repeated additions of n
    Num mul(Num other) {
        if (!(this.isValid() && other.isValid())) {
            return new Num(Optional.<Integer>empty());
        }
        if (this.equals(Num.zero()) || other.equals(Num.zero())) {
            return Num.zero();
        }
        Num counter = Num.one();
        Num ans = this;
        Num initial = this;
        while (!counter.equals(other)) {
            ans = ans.add(initial);
            counter = counter.succ();
        }
        return ans;
    }

    // m.sub(n) = (-n).add(m); negate n (without invalidating it) then add m to it
    Num sub(Num other) {
        Num initial = new Num(other.opt.map(x -> n.apply(x)));
        initial = initial.add(this);
        // filter so that if opt contains <0 , then it becomes empty -> NaN
        return new Num(initial.opt.filter(x -> valid.test(x)));
    }

}
