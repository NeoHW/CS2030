class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public String toString() {
        String x = String.format("%.3f", this.x);
        String y = String.format("%.3f", this.y);
        return "point (" + x + ", " + y + ")";
    }

    public Point midPoint(Point other) {
        return new Point((this.x + other.x) / 2, (this.y + other.y) / 2);
    }
    
    public double angleTo(Point other) {
        return Math.atan2((other.y - this.y), (other.x - this.x));
    }

    public Point moveTo(double angle, double distance) {
        return new Point(this.x + distance * Math.cos(angle), this.y + distance * Math.sin(angle));
    }

    public double distanceBetween(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
    
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }
}

