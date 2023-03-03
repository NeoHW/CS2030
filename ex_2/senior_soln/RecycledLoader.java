class RecycledLoader extends Loader {
    private static final int REST_TIME = 60;

    RecycledLoader(int id) {
        super(id);
    }

    @Override
    public int getRest() {
        return REST_TIME;
    }

    @Override
    public String toString() {
        return "Recycled " + super.toString();
    }
}
