package org.hbrs.se1.ws22.uebung10;

public class MyPoint {

    public double x;
    public double y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyPoint) {
            return (x == ((MyPoint) obj).x) && (y == ((MyPoint) obj).y);
        }
        return super.equals(obj);
    }
}
