class ART extends Test {

    ART(String result) {
        super(getValidity(result), getResult(result));
    }

    static boolean getValidity(String result) {
        switch (result) {
            case "C":
            case "CT":
                return true;
            default:
                return false;
        }
    }

    static boolean getResult(String result) {
        switch (result) {
            case "C":
                return false;
            case "CT":
                return true;
            default:
                return false;

        }
    }

    @Override
    public String toString() {
        return "A" + super.toString();
    }
}
