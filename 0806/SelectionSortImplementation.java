public class SelectionSortImplementation {
    public static void selectionSort(int[] A) {
        int n = A.length, comp = 0, swap = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comp++;
                if (A[j] < A[minIdx]) minIdx = j;
            }
            if (i != minIdx) {
                int tmp = A[i];
                A[i] = A[minIdx];
                A[minIdx] = tmp;
                swap++;
            }
            System.out.print("Round " + (i + 1) + ": ");
            printArray(A);
        }
        System.out.println("比較次數: " + comp);
        System.out.println("交換次數: " + swap);
    }

    public static void printArray(int[] A) {
        for (int x : A) System.out.print(x + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] A = {64, 25, 12, 22, 11};
        System.out.print("原始陣列: ");
        printArray(A);
        selectionSort(A);
    }
}
