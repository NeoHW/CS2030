enum DischargedStatus {

    FOLLOW_UP("follow up with doctor"),
    MEDICAL_ATTN("seek medical attention"),
    COMPLETE("complete");


    private final String message;

    DischargedStatus(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
