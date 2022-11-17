package org.nuvalence.rectangles.enums;

public enum IntersectionType {
    NO_INTERSECTION("No Intersection"),
    INTERSECTION("Intersection"),
    CONTAINMENT("Containment"),
    ADJACENT_SUB_LINE("Adjacent (Sub-line)"),
    ADJACENT_PROPER("Adjacent (Proper)"),
    ADJACENT_PARTIAL("Adjacent (Partial)");

    IntersectionType(String s) {
    }
}
