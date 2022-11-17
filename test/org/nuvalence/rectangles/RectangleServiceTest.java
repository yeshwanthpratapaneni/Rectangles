package org.nuvalence.rectangles;

import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.nuvalence.rectangles.enums.IntersectionType;
import org.nuvalence.rectangles.model.Rectangle;
import org.nuvalence.rectangles.result.IntersectionResult;
import org.nuvalence.rectangles.service.RectangleService;

import static junit.framework.TestCase.assertEquals;

@RequiredArgsConstructor
public class RectangleServiceTest {
    private RectangleService rectangleService = new RectangleService();

    @Test
    public void noIntersectionTest() {
        Rectangle r1 = buildRectangle(0, 1, 0 , 1);
        Rectangle r2 = buildRectangle(2,3,2,3);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.NO_INTERSECTION, result.getIntersectionType());
    }

    @Test
    public void intersectionTest() {
        Rectangle r1 = buildRectangle(0, 3, 0,3);
        Rectangle r2 = buildRectangle(1,4,1,4);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.INTERSECTION, result.getIntersectionType());
    }

    @Test
    public void containmentTest() {
        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(1,4,1,4);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.CONTAINMENT, result.getIntersectionType());
    }

    @Test
    public void adjacencyPartialTest() {
        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(7,12,2,9);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.ADJACENT_PARTIAL, result.getIntersectionType());
    }

    @Test
    public void adjacencySubLineTest() {
        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(7,15,2,3);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.ADJACENT_SUB_LINE, result.getIntersectionType());
    }

    @Test
    public void adjacencyProperTest() {
        Rectangle r1 = buildRectangle(0, 7, 0,7);
        Rectangle r2 = buildRectangle(7,15,0,7);

        IntersectionResult result = rectangleService.compareRectangle(r1, r2);
        assertEquals(IntersectionType.ADJACENT_PROPER, result.getIntersectionType());
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
