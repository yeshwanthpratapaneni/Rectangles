package org.nuvalence.rectangles.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Rectangle {
    int x1;
    int y1;
    int x2;
    int y2;
}
