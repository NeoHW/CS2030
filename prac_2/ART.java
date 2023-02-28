class ART implements Test {
    private final String result;

    ART(String result) {
        this.result = result;
    }

    public String getResult() {
        if (this.result.equals("C")) {
            return "-";
        } else if (this.result.equals("CT")) {
            return "+";
        } else {
            return "X";
        }
    }

    @Override
    public boolean isValid() {
        return !this.result.equals("T");
    }

    @Override
    public boolean isPositive() {
        return this.result.equals("CT");
    }

    @Override
    public String toString() {
        return "A" + this.getResult();
    }
}
