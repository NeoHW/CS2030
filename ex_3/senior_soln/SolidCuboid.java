class SolidCuboid extends Cuboid implements Solid {
    private final SolidImpl solid;

    SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.solid = new SolidImpl(this, density);
    }

    @Override
    public double mass() {
        return this.solid.mass();
    }

    @Override
    public String toString() {
        return String.format("solid-%s with a mass of %.2f", super.toString(), this.mass());
    }
}