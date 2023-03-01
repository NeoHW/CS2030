abstract class Test {

    private final boolean isValid;
    private final boolean isPositive;

    Test(boolean isValid, boolean isPositive) {
        this.isValid = isValid;
        this.isPositive = isPositive;
    }

    boolean isValid() {
        return isValid;
    }

    boolean isPositive() {
        return isPositive;
    }

    @Override
    public String toString() {
        if (!isValid()) {
            return "X";
        } else if (isPositive()) {
            return "+";
        } else {
            return "-";
        }
    }
}
