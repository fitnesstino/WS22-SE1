package org.hbrs.se1.ws22.uebung10;

public class BoundingBoxFactory {
    public static MyPrettyRectangle createBoundingBox(MyPrettyRectangle[] rectangles) {
        if (rectangles == null) return null;

        if (rectangles.length == 0) return new MyPrettyRectangle(0, 0, 0, 0);

        double xmin = Double.POSITIVE_INFINITY, ymin = Double.POSITIVE_INFINITY, xmax = Double.NEGATIVE_INFINITY, ymax = Double.NEGATIVE_INFINITY;

        for (MyPrettyRectangle rectangle : rectangles) {
            xmin = rectangle.getP1().x < xmin ? rectangle.getP1().x : xmin;
            ymin = rectangle.getP1().y < ymin ? rectangle.getP1().y : ymin;

            xmax = rectangle.getP2().x > xmax ? rectangle.getP2().x : xmax;
            ymax = rectangle.getP2().y > ymax ? rectangle.getP2().y : ymax;
        }

        return new MyPrettyRectangle(xmin, ymin, xmax, ymax);
    }
}
