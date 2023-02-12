class Circle {
    private final Point centre;
    private final double radius;
    private static final double epsilon = 1E-15;

    Circle(Point centre, double radius) {
        this.centre = centre;
        this.radius = radius;
    }

    public String toString() {
        return "circle of radius " + this.radius + " centred at " + this.centre;
    }
    
    public boolean contains(Point p) {
        return this.centre.distanceBetween(p) < this.radius + epsilon;
    }
}
