package org.nuvalence.rectangles.utils;

import org.nuvalence.rectangles.model.Point;
import org.nuvalence.rectangles.model.Rectangle;

import java.util.Set;

public interface IRectangleUtil {
    Set<Point> generatePoints(Rectangle r);
    Set<Point> getIntersectionPoints(Set<Point> points1, Set<Point> points2);
    boolean isContained(Rectangle r1, Rectangle r2);
    boolean checkForBoundaryIntersection(Set<Point> intersectedPoints, Rectangle r1, Rectangle r2);
}
