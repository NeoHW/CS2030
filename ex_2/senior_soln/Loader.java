class Loader {
    private static final int REST_TIME = 0;
    private final int id;

    Loader(int id) {
        this.id = id;
    }

    int getRest() {
        return REST_TIME;
    }

    @Override
    public String toString() {
        return "Loader #" + this.id;
    }
}
