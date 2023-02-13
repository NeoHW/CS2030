public class Cuboid implements Shape3D {
    private final double height;
    private final double width;
    private final double length;

    Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }

    public double volume() {
        return this.height * this.width * this.length;
    }

    @Override
    public String toString() {
        String height = String.format("%.2f", this.height);
        String width = String.format("%.2f", this.width);
        String length = String.format("%.2f", this.length);
        return "cuboid [" +  height + " x " + width + " x " + length + "]";
    }

}
