public class SolidSphere extends Sphere {

    private final double density;

    SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }

    public double mass() {
        return super.volume() * this.density;
    }

    @Override
    public String toString() {
        String mass = String.format("%.2f", this.mass());
        return "solid-" + super.toString() + " with a mass of " + mass; 
    }

}
