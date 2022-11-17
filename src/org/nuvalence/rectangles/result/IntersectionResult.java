package org.nuvalence.rectangles.result;

import lombok.Builder;
import lombok.Data;
import org.nuvalence.rectangles.enums.IntersectionType;
import org.nuvalence.rectangles.model.Point;
import org.nuvalence.rectangles.model.Rectangle;

import java.util.Set;

@Builder
@Data
public class IntersectionResult {
    private IntersectionType intersectionType;
    private Rectangle container;
    private Rectangle containee;
    private Set<Point> intersectionPoints;
}
