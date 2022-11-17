package org.nuvalence.rectangles.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o){
        final Point p = (Point) o;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public int hashCode() {
        int tmp = ( y +  ((x+1)/2));
        return x +  ( tmp * tmp);
    }
}
