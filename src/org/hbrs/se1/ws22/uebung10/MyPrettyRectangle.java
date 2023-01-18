package org.hbrs.se1.ws22.uebung10;

public class MyPrettyRectangle {

    private MyPoint p1, p2;

    public MyPrettyRectangle(double x1, double y1, double x2, double y2) {
        p1 = new MyPoint(x1, y1);
        p2 = new MyPoint(x2, y2);
    }

    public boolean contains(MyPrettyRectangle rectangle) {
        if(rectangle==null) return false;
        return (p1.x <= rectangle.p1.x) && (p1.y <= rectangle.p2.y) && (rectangle.p2.x <= p2.x) && (rectangle.p2.y <= p2.y);
    }

    public MyPoint getCenter() {
        double x = p1.x + (p2.x - p1.x) / 2;
        double y = p1.y + (p2.y - p1.y) / 2;
        return new MyPoint(x, y);
    }

    public double getPerimeter(){
        return 2*(p2.x-p1.x)+2*(p2.y-p1.y);
    }

    public double getArea(){
        return (p2.x-p1.x)*(p2.y-p1.y);
    }

    public MyPoint getP1() {
        return p1;
    }

    public MyPoint getP2() {
        return p2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MyPrettyRectangle) {
            return p1.equals(((MyPrettyRectangle) obj).p1) && p2.equals(((MyPrettyRectangle) obj).p2);
        }
        return super.equals(obj);
    }
}
