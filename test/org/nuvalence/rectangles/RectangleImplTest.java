package org.nuvalence.rectangles;

import org.junit.Test;
import org.nuvalence.rectangles.model.Point;
import org.nuvalence.rectangles.model.Rectangle;
import org.nuvalence.rectangles.utils.IRectangleUtil;
import org.nuvalence.rectangles.utils.impl.RectangleUtilImpl;

import java.util.Set;

import static org.junit.Assert.*;

public class RectangleImplTest {

    private IRectangleUtil rectangleUtil = new RectangleUtilImpl();

    @Test
    public void generatePointsTest() {
        Rectangle rectangle = buildRectangle(0,2,0,2);
        Set<Point> generatedPoints = rectangleUtil.generatePoints(rectangle);

        assertNotNull(generatedPoints);
        assertEquals(8, generatedPoints.size());
    }

    @Test
    public void getIntersectionPointsTest() {
        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(7,12,2,9);

        Set<Point> points1 = rectangleUtil.generatePoints(r1);
        Set<Point> points2 = rectangleUtil.generatePoints(r2);

        Set<Point> intersectionPoints = rectangleUtil.getIntersectionPoints(points1, points2);
        assertNotNull(intersectionPoints);
        assertEquals(6, intersectionPoints.size());
    }

    @Test
    public void isContainedTest() {
        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(1,4,1,4);

        boolean result = rectangleUtil.isContained(r1, r2);
        assertTrue(result);
    }

    @Test
    public void boundaryIntersectioTest() {
        Rectangle r1 = buildRectangle(0, 3, 0,3);
        Rectangle r2 = buildRectangle(1,4,1,4);

        Set<Point> points1 = rectangleUtil.generatePoints(r1);
        Set<Point> points2 = rectangleUtil.generatePoints(r2);

        Set<Point> intersectionPoints = rectangleUtil.getIntersectionPoints(points1, points2);
        boolean result = rectangleUtil.checkForBoundaryIntersection(intersectionPoints, r1, r2);

        assertFalse(result);
    }

    private Rectangle buildRectangle(int x1, int x2, int y1, int y2){
        return Rectangle.builder()
                .x1(x1)
                .y1(y1)
                .x2(x2)
                .y2(y2)
                .build();
    }
}
