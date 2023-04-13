import java.util.Optional;
import java.util.stream.Stream;

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
    /* stream implementation: 
        return Stream.iterate(Num.zero(), x -> !x.equals(other). x -> x.succ())
            .reduce(this, (x,y) -> x.succ())
     */
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

    // for level 6
    Num gcd(Num other) {
        if (this.equals(Num.zero()) || other.equals(Num.zero())) {
            return Num.one();
        }

        /*  Stream implementation
        return Stream.iterate(Num.one(), x -> !x.equals(this) && !x.equals(other), x -> x.succ())
            .map(x -> x.succ())
            .reduce(Num.one(), (x, y) -> y.divides(this) && y.divides(other) ? y : x);
        */

        // loop implementation
        Num result = Num.one();
        Num current = Num.one();

        while (!current.equals(this) && !current.equals(other)) {
            current = current.succ();

            if (current.divides(this) && current.divides(other)) {
                result = current;
            }
        }
        return result;
    }

    boolean divides(Num other) {
        /*  stream implementation
        return Stream.iterate(other, x -> x.isValid() && !x.equals(Num.zero()), x -> x.sub(this))
            .anyMatch(x -> x.sub(this).equals(Num.zero()));
        */

        // loop implementation
        Num temp = other;

        // this : 3, other : 6
        // check whether 3 is a factor of 6
        // while (temp > 0)
            // temp = temp - this
        while (temp.isValid() && !temp.equals(Num.zero())) {
            temp = temp.sub(this);
        }
        // 3 is a factor of 6 is my final temp is == 0
        return temp.equals(Num.zero());
    }

    // Returns this divided by other
    // Assumes that other is a divisor of this
    Num div(Num other) {
        if (!(this.isValid() && other.isValid())) {
            return new Num(Optional.<Integer>empty());
        }

        /*  stream implementation
        return Stream.iterate(Num.one(), x -> !x.mul(other).equals(this), x -> x.succ())
            .map(x -> x.succ())
            .reduce(Num.one(), \* redundant *\ (x,y) -> y);
        */

        // 8 / 2
        // result = 1
        // 1 * 2  ==> 2
        // 2 * 2 ==> 4
        // ...
        // 4 * 2 ==> 8
        // result = 4

        // loop implementation
        Num result = Num.one();
        while (!result.mul(other).equals(this)) {
            result = result.succ();
        }

        return result;
    }
}
