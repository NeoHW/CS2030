class Loader {
    private final int loaderNum;

    Loader(int loaderNum) {
        this.loaderNum = loaderNum;
    }

    public int getLoaderNum() {
        return this.loaderNum;
    }
    
    @Override
    public String toString() {
        return "Loader #" + this.loaderNum;
    }
}
