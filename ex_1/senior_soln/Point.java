class Point {
    private final double x;
    private final double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Point midPoint(Point other) {
        double newX = this.x + (other.x - this.x) / 2;
        double newY = this.y + (other.y - this.y) / 2;
        return new Point(newX, newY);
    }

    double distanceTo(Point other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    double angleTo(Point other) {
        double x = other.x - this.x;
        double y = other.y - this.y;
        return angle(new Point(x, y)) - angle(new Point(0, 0));
    }

    Point moveTo(double angle, double distance) {
        return new Point(this.x + distance * Math.cos(angle), 
                this.y + distance * Math.sin(angle));
    }

    double angle(Point point) {
        return Math.atan2(point.y, point.x);
    }

    @Override
    public String toString() {
        return String.format("point (%.3f, %.3f)", this.x, this.y);
    }
}
