package org.nuvalence.rectangles;

import org.nuvalence.rectangles.model.Rectangle;
import org.nuvalence.rectangles.result.IntersectionResult;
import org.nuvalence.rectangles.service.RectangleService;

public class RectanglesExample {

    public static void main(String[] args) {
        RectanglesExample rectanglesExample = new RectanglesExample();
        RectangleService service = new RectangleService();

        Rectangle r1 = rectanglesExample.buildRectangle(0, 3, 0,3);
        Rectangle r2 = rectanglesExample.buildRectangle(1,4,1,4);

        IntersectionResult result = service.compareRectangle(r1, r2);
        System.out.println("Type of Intersection : "+result.getIntersectionType());
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
