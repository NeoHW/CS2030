import java.util.Optional;

class Fraction extends AbstractNum<Frac> {

    private Fraction(Num n, Num d) {
        super(Frac.of(n, d));
    }

    private Fraction(Optional<Frac> opt) {
        super(opt);
    }

    static Fraction of(int x, int y) {
        Num n = Num.of(x);
        Num d = Num.of(y);

        // case when denominator is 0
        if (d.equals(Num.zero())) {
            return new Fraction(Optional.<Frac>empty());
        }

        // case when denom or numerator is negative
        if (!(n.isValid() && d.isValid())) {
            return new Fraction(Optional.<Frac>empty());
        }

        return new Fraction(n, d);
    }

    Fraction add(Fraction other) {
        if (!(this.isValid() && other.isValid())) {
            return new Fraction(Optional.<Frac>empty());
        }
        
        Optional<Num> optA = this.opt.map(x -> x.first());
        Optional<Num> optB = this.opt.map(x -> x.second());
        Optional<Num> optC = other.opt.map(x -> x.first());
        Optional<Num> optD = other.opt.map(x -> x.second());

        Optional<Num> optAD = optA.flatMap(x -> optD.map(y -> x.mul(y)));
        Optional<Num> optBC = optB.flatMap(x -> optC.map(y -> x.mul(y)));
        Optional<Num> optNumerator = optAD.flatMap(x -> optBC.map(y -> x.add(y)));
        Optional<Num> optDenom = optB.flatMap(x -> optD.map(y -> x.mul(y)));
        return new Fraction(optNumerator.flatMap(x -> optDenom.map(
                    y -> Frac.of(x, y))));
    }

    Fraction sub(Fraction other) {
        if (!(this.isValid() && other.isValid())) {
            return new Fraction(Optional.<Frac>empty());
        }

        Optional<Num> optA = this.opt.map(x -> x.first());
        Optional<Num> optB = this.opt.map(x -> x.second());
        Optional<Num> optC = other.opt.map(x -> x.first());
        Optional<Num> optD = other.opt.map(x -> x.second());

        Optional<Num> optAD = optA.flatMap(x -> optD.map(y -> x.mul(y)));
        Optional<Num> optBC = optB.flatMap(x -> optC.map(y -> x.mul(y)));
        Optional<Num> optNumerator = optAD.flatMap(x -> optBC.map(y -> x.sub(y)));
        Optional<Num> optDenom = optB.flatMap(x -> optD.map(y -> x.mul(y)));

        return new Fraction(optNumerator.flatMap(x -> optDenom.flatMap(
            y -> {
                if (x.isValid() && y.isValid()) {
                    return Optional.of(Frac.of(x,y));
                } else {
                    return Optional.<Frac>empty();
                }
            })));
    }

    Fraction mul(Fraction other) {
        if (!(this.isValid() && other.isValid())) {
            return new Fraction(Optional.<Frac>empty());
        }

        Optional<Num> optA = this.opt.map(x -> x.first());
        Optional<Num> optB = this.opt.map(x -> x.second());
        Optional<Num> optC = other.opt.map(x -> x.first());
        Optional<Num> optD = other.opt.map(x -> x.second());

        Optional<Num> optNumerator = optA.flatMap(x -> optC.map(y -> x.mul(y)));
        Optional<Num> optDenom = optB.flatMap(x -> optD.map(y -> x.mul(y)));

        return new Fraction(optNumerator.flatMap(x -> optDenom.flatMap(
            y -> {
                if (x.isValid() && y.isValid()) {
                    return Optional.of(Frac.of(x,y));
                } else {
                    return Optional.<Frac>empty();
                }
            })));
    }

    // a/b ==>
    // c ==> gcd(a,b); gcd is greatest common divisor
    Fraction reduce()  {
        return new Fraction(this.opt.flatMap(x -> {
            Num n = x.first();
            Num d = x.second();
            Num gcd = n.gcd(d);
            return Optional.<Frac>of(Frac.of(n.div(gcd), d.div(gcd)));
        }));
    }
}
