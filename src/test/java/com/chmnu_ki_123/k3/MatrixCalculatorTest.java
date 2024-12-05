package com.chmnu_ki_123.k3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatrixCalculatorTest {

    @Test
    void testAddMatrices() {
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{5, 6}, {7, 8}};
        int[][] expected = {{6, 8}, {10, 12}};
        assertArrayEquals(expected, MatrixCalculator.addMatrices(matrix1, matrix2));
    }

    @Test
    void testSubtractMatrices() {
        int[][] matrix1 = {{5, 6}, {7, 8}};
        int[][] matrix2 = {{1, 2}, {3, 4}};
        int[][] expected = {{4, 4}, {4, 4}};
        assertArrayEquals(expected, MatrixCalculator.subtractMatrices(matrix1, matrix2));
    }

    @Test
    void testInvalidMatrixDimensions() {
        int[][] matrix1 = {{1, 2, 3}};
        int[][] matrix2 = {{4, 5}};
        assertThrows(IllegalArgumentException.class, () -> MatrixCalculator.addMatrices(matrix1, matrix2));
    }
}
