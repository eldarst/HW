package com.eldarst.resourcepool.calculation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.sqrt;

public class TestSolver {
    @Test
    void testGetRootsNoRoot() {
        double a = 8, b = 5, c = 2;
        Assertions.assertEquals("D < 0\n", Solver.getRoots(a, b, c));
    }

    @Test
    void testGetRootsOneRoot() {
        double a = 1, b = 2, c = 1;
        double x = (-b / (2 * a));
        Assertions.assertEquals(String.format("%f" + "\n", x), Solver.getRoots(a, b, c));
    }

    @Test
    void testGetRootsTwoRoots() {
        double a = 2, b = 8, c = 2;
        double discriminant = sqrt(b * b - 4 * a * c);
        double x1 = (-b + discriminant) / (2 * a);
        double x2 = (-b - discriminant) / (2 * a);
        Assertions.assertEquals(String.format("%f, %f" + "\n", x1, x2), Solver.getRoots(a, b, c));
    }

}
