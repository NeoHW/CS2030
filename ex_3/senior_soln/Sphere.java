class Sphere implements Shape3D {
    private final double radius;
    private static final double FRACTION = 4.0 / 3;
    private static final double POWER = 3;

    Sphere(double radius) {
        this.radius = radius;
    }

    @Override
    public double volume() {
        return FRACTION * Math.PI * Math.pow(this.radius, POWER);
    }

    @Override
    public String toString() {
        return String.format("sphere [%.2f]", this.radius);
    }
}