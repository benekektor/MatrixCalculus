package com.chmnu_ki_123.k3;

import java.io.*;
import java.util.*;

public class MatrixCalculator {

    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        List<int[][]> matrices = readMatricesFromFile(inputFile);

        int[][] matrix1 = matrices.get(0);
        int[][] matrix2 = matrices.get(1);

        int[][] sum = addMatrices(matrix1, matrix2);
        int[][] difference = subtractMatrices(matrix1, matrix2);

        writeMatricesToFile(outputFile, sum, difference);
    }

    public static List<int[][]> readMatricesFromFile(String fileName) throws IOException {
        List<int[][]> matrices = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            List<int[]> currentMatrix = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) { // Empty line separates matrices
                    matrices.add(listToMatrix(currentMatrix));
                    currentMatrix.clear();
                } else {
                    currentMatrix.add(Arrays.stream(line.trim().split("\\s+"))
                            .mapToInt(Integer::parseInt)
                            .toArray());
                }
            }
            if (!currentMatrix.isEmpty()) {
                matrices.add(listToMatrix(currentMatrix));
            }
        }
        if (matrices.size() != 2) {
            throw new IllegalArgumentException("Input file must contain exactly two matrices.");
        }
        return matrices;
    }

    private static int[][] listToMatrix(List<int[]> list) {
        return list.toArray(new int[0][]);
    }

    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {
        validateMatrices(matrix1, matrix2);
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static int[][] subtractMatrices(int[][] matrix1, int[][] matrix2) {
        validateMatrices(matrix1, matrix2);
        int rows = matrix1.length;
        int cols = matrix1[0].length;
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    private static void validateMatrices(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            throw new IllegalArgumentException("Matrices must have the same dimensions.");
        }
    }

    public static void writeMatricesToFile(String fileName, int[][] sum, int[][] difference) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("Sum:\n");
            writeMatrix(bw, sum);
            bw.write("\nDifference:\n");
            writeMatrix(bw, difference);
        }
    }

    private static void writeMatrix(BufferedWriter bw, int[][] matrix) throws IOException {
        for (int[] row : matrix) {
            bw.write(Arrays.toString(row).replaceAll("[\\[\\],]", ""));
            bw.newLine();
        }
    }
}
