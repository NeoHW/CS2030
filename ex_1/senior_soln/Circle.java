class Circle {
    private final Point centre;
    private final double radius;
    private static final double EPSILON = 1E-15;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    boolean contains(Point p) {
        return this.centre.distanceTo(p) < this.radius + EPSILON;
    }

    @Override
    public String toString() {
        return String.format("circle of radius %.1f centred at ", this.radius) + this.centre;
    }
}
