class SolidSphere extends Sphere implements Solid {
    private final SolidImpl solid;

    SolidSphere(double radius, double density) {
        super(radius);
        this.solid = new SolidImpl(this, density);
    }

    @Override
    public double mass() {
        return this.solid.mass();
    }

    @Override
    public double volume() {
        return super.volume();
    }

    @Override
    public String toString() {
        return String.format("solid-%s with a mass of %.2f", super.toString(), this.mass());
    }
}