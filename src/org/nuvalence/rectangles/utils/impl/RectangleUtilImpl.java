package org.nuvalence.rectangles.utils.impl;

import org.nuvalence.rectangles.model.Point;
import org.nuvalence.rectangles.model.Rectangle;
import org.nuvalence.rectangles.utils.IRectangleUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RectangleUtilImpl implements IRectangleUtil {
    @Override
    public Set<Point> generatePoints(Rectangle r) {

        int x1 = r.getX1();
        int x2 = r.getX2();
        int y1 = r.getY1();
        int y2 = r.getY2();

        Set<Point> points = new HashSet<>();

        // bottom left to bottom right
        for(int x = x1; x <= x2; x++)
            points.add(new Point(x, y1));

        // bottom right to top right
        for(int y = y1; y <= y2; y++)
            points.add(new Point(x2, y));

        // from top right to top left
        for(int x = x2; x >= x1; x--)
            points.add(new Point(x, y2));

        // from top left to bottom left
        for(int y = y2; y >= y1; y--)
            points.add(new Point(x1, y));

        return points;
    }

    @Override
    public Set<Point> getIntersectionPoints(Set<Point> points1, Set<Point> points2) {
        Set<Point> intersectedPoints = new HashSet<>();

        for(Point point : points1){
            if(points2.contains(point))
                intersectedPoints.add(point);
        }

        return intersectedPoints;
    }

    @Override
    public boolean isContained(Rectangle r1, Rectangle r2) {
        return r1.getX1() <= r2.getX1() && r1.getY1() <= r2.getY2() && r1.getX2() >= r2.getX2() && r1.getY2() >= r2.getY2();
    }

    @Override
    public boolean checkForBoundaryIntersection(Set<Point> intersectedPoints, Rectangle r1, Rectangle r2) {
        List<Point> list = new ArrayList<>(intersectedPoints);

        Point p1 = list.get(0);
        Point p2 = list.get(1);

        if((p1.getX() == r1.getX1() && p1.getY() == r1.getY1())
                || (p2.getX() == r1.getX1() && p2.getY() == r1.getY1())
                || (p1.getX() == r2.getX1() && p1.getY() == r2.getY1())
                || (p2.getX() == r2.getX1() && p2.getY() == r2.getY1())
        )
            return true;

        return false;
    }
}
