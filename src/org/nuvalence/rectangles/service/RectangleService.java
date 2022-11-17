package org.nuvalence.rectangles.service;

import org.nuvalence.rectangles.result.IntersectionResult;
import org.nuvalence.rectangles.enums.IntersectionType;
import org.nuvalence.rectangles.model.Point;
import org.nuvalence.rectangles.model.Rectangle;
import org.nuvalence.rectangles.utils.IRectangleUtil;
import org.nuvalence.rectangles.utils.impl.RectangleUtilImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RectangleService {

    private IRectangleUtil rectangleUtil = new RectangleUtilImpl();

    public IntersectionResult compareRectangle(Rectangle r1, Rectangle r2) {
        Set<Point> points1 = rectangleUtil.generatePoints(r1);
        Set<Point> points2 = rectangleUtil.generatePoints(r2);

        Set<Point> intersectionPoints = rectangleUtil.getIntersectionPoints(points1, points2);

        if(intersectionPoints.size() == 0)
            return analyzeContainment(r1, r2);
        else
            return analyzeAdjacentOrRegularIntersection(intersectionPoints, r1, r2);
    }

    public IntersectionResult analyzeAdjacentOrRegularIntersection(Set<Point> intersectionPoints, Rectangle r1, Rectangle r2){
        if(intersectionPoints.size() == 2 && !rectangleUtil.checkForBoundaryPoints(intersectionPoints, r1, r2))
            return IntersectionResult.builder()
                    .intersectionType(IntersectionType.INTERSECTION)
                    .intersectionPoints(intersectionPoints)
                    .build();

        int h1 = Math.abs(r1.getY2()) - Math.abs(r1.getY1()) + 1;
        int w1 = Math.abs(r1.getX2()) - Math.abs((r1.getX1())) + 1;

        int h2 = Math.abs((r2.getY2())) - Math.abs(r2.getY1()) + 1;
        int w2 = Math.abs((r1.getX2())) - Math.abs(r1.getX1()) + 1;

        if((h1 == h2 && intersectionPoints.size() == h1)
            || (h1 == w2 && intersectionPoints.size() == h1)
            || (w1 == h2 && intersectionPoints.size() == w1)
            || (w1 == w2 && intersectionPoints.size() == w1)){
            return IntersectionResult.builder()
                    .intersectionType(IntersectionType.ADJACENT_PROPER)
                    .intersectionPoints(intersectionPoints)
                    .build();
        }

        if(intersectionPoints.size() == h1
            || intersectionPoints.size() == h2
            || intersectionPoints.size() == w1
            || intersectionPoints.size() == w2){
            return IntersectionResult.builder()
                    .intersectionType(IntersectionType.ADJACENT_SUB_LINE)
                    .intersectionPoints(intersectionPoints)
                    .build();
        }else{
            return IntersectionResult.builder()
                    .intersectionType(IntersectionType.ADJACENT_PARTIAL)
                    .intersectionPoints(intersectionPoints)
                    .build();
        }
    }


    public IntersectionResult analyzeContainment(Rectangle r1, Rectangle r2) {
        // Lets check if R1 will contain R2
        if(rectangleUtil.isContained(r1, r2)){
            // this implies R1 is the container and R2 is the containee
            return IntersectionResult.builder()
                    .intersectionType(IntersectionType.CONTAINMENT)
                    .container(r1)
                    .containee(r2)
                    .build();
        }else if(rectangleUtil.isContained(r2, r1)){
            return IntersectionResult.builder()
                    .intersectionType(IntersectionType.CONTAINMENT)
                    .container(r2)
                    .containee(r1)
                    .build();
        }else{
            return IntersectionResult.builder()
                    .intersectionType(IntersectionType.NO_INTERSECTION)
                    .build();
        }
    }

}
