class PCR extends Test {

    PCR(String result) {
        super(true, getResult(result));
    }

    static boolean getResult(String result) {
        switch (result) {
            case "alpha":
            case "beta":
            case "delta":
            case "omicron":
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "P" + super.toString();
    }
}
