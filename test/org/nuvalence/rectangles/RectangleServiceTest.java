package org.nuvalence.rectangles;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.nuvalence.rectangles.enums.IntersectionType;
import org.nuvalence.rectangles.model.Rectangle;
import org.nuvalence.rectangles.result.IntersectionResult;
import org.nuvalence.rectangles.service.RectangleService;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RequiredArgsConstructor
public class RectangleServiceTest {
    private RectangleService rectangleService = new RectangleService();

    @Test
    public void noIntersectionTest() {
        Rectangle r1 = buildRectangle(0, 1, 0 , 1);
        Rectangle r2 = buildRectangle(2,3,2,3);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.NO_INTERSECTION, result.getIntersectionType());
        assertNull(result.getIntersectionPoints());
    }

    @Test
    public void intersectionTest() {
        Rectangle r1 = buildRectangle(0, 3, 0,3);
        Rectangle r2 = buildRectangle(1,4,1,4);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.INTERSECTION, result.getIntersectionType());
        assertEquals(2, result.getIntersectionPoints().size());
    }

    @Test
    public void containmentTest() {

        // Rectangle R1 Completely contains R2
        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(1,4,1,4);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.CONTAINMENT, result.getIntersectionType());
        assertNull(result.getIntersectionPoints());
        assertEquals(r1, result.getContainer());
        assertEquals(r2, result.getContainee());
    }

    @Test
    public void adjacencyPartialTest() {
        // Both R1 and R2 are adjacent, however only a partial side is adjacent
        // There are 6 Intersection points in the following two rectangles

        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(7,12,2,9);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.ADJACENT_PARTIAL, result.getIntersectionType());
        assertEquals(6, result.getIntersectionPoints().size());
    }

    @Test
    public void adjacencySubLineTest() {
        // Both R1 and R2 are adjacent, however one side of the rectangle is
        // completely covered in the other's adjacent side hence this implies a adjacent sub line rectangle overlap
        // 2 points of rectangle r2 is completely covered in the other rectangle r1

        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(7,15,2,3);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.ADJACENT_SUB_LINE, result.getIntersectionType());
        assertEquals(2, result.getIntersectionPoints().size());
    }

    @Test
    public void adjacencyProperTest() {
        // Both R1 and R2 are adjacent and there is a complete overlap of the sides
        // hence this implies a adjacent proper rectangle overlap
        // 8 points of rectangle r1 and r2 are intersected on one of their sides

        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(7,15,0,7);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.ADJACENT_PROPER, result.getIntersectionType());
        assertEquals(8, result.getIntersectionPoints().size());
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
