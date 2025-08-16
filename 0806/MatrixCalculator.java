
public class MatrixCalculator {
    public static int[][] add(int[][] A, int[][] B) {
        int[][] result = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[A.length][B[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B[0].length; j++)
                for (int k = 0; k < B.length; k++)
                    result[i][j] += A[i][k] * B[k][j];
        return result;
    }

    public static int[][] transpose(int[][] A) {
        int[][] result = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                result[j][i] = A[i][j];
        return result;
    }

    public static int[] findMinMax(int[][] A) {
        int min = A[0][0], max = A[0][0];
        for (int[] row : A)
            for (int val : row) {
                if (val < min) min = val;
                if (val > max) max = val;
            }
        return new int[]{min, max};
    }

    public static void printMatrix(int[][] M) {
        for (int[] row : M) {
            for (int val : row)
                System.out.print(val + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] A = {{1, 2}, {3, 4}};
        int[][] B = {{5, 6}, {7, 8}};
        System.out.println("加法:");
        printMatrix(add(A, B));
        System.out.println("乘法:");
        printMatrix(multiply(A, B));
        System.out.println("轉置 A:");
        printMatrix(transpose(A));
        int[] minmax = findMinMax(A);
        System.out.println("最大值: " + minmax[1] + ", 最小值: " + minmax[0]);
    }
}
