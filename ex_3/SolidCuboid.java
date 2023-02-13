public class SolidCuboid extends Cuboid {

    private final double density;

    SolidCuboid(double height, double width, double length, double density) {
        super(height,width,length);
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
