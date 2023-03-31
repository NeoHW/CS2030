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
}
