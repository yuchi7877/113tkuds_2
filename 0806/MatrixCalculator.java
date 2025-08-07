import java.util.Scanner;

public class MatrixCalculator {

    public static int[][] add(int[][] A, int[][] B) {
        int rows = A.length, cols = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[i][j] = A[i][j] + B[i][j];

        return result;
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int rowsA = A.length, colsA = A[0].length;
        int rowsB = B.length, colsB = B[0].length;

        if (colsA != rowsB) throw new IllegalArgumentException("矩陣無法相乘");

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++)
            for (int j = 0; j < colsB; j++)
                for (int k = 0; k < colsA; k++)
                    result[i][j] += A[i][k] * B[k][j];

        return result;
    }

    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                result[j][i] = matrix[i][j];

        return result;
    }

    public static int[] findMinMax(int[][] matrix) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int[] row : matrix)
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        return new int[]{min, max};
    }

    public static void printMatrix(String name, int[][] matrix) {
        System.out.println(name + ":");
        for (int[] row : matrix) {
            for (int val : row)
                System.out.printf("%4d", val);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] A = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] B = {
            {7, 8, 9},
            {10, 11, 12}
        };
        int[][] C = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        int[][] sum = add(A, B);
        printMatrix("矩陣 A", A);
        printMatrix("矩陣 B", B);
        printMatrix("A + B", sum);
        System.out.println();

        int[][] product = multiply(A, C);
        printMatrix("A * C", product);
        System.out.println();
        
        int[][] A_T = transpose(A);
        printMatrix("A 的轉置", A_T);
        System.out.println();

        int[] minMax = findMinMax(A);
        System.out.println("A 中的最小值: " + minMax[0]);
        System.out.println("A 中的最大值: " + minMax[1]);
    }
}
