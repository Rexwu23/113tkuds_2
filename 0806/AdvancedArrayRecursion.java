import java.util.*;

public class AdvancedArrayRecursion {

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int pivot = arr[right];
        int i = left, j = right - 1;
        while (i <= j) {
            if (arr[i] < pivot) i++;
            else {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j--] = temp;
            }
        }
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    public static int[] mergeSorted(int[] A, int[] B) {
        return mergeHelper(A, B, 0, 0);
    }

    private static int[] mergeHelper(int[] A, int[] B, int i, int j) {
        if (i == A.length) return Arrays.copyOfRange(B, j, B.length);
        if (j == B.length) return Arrays.copyOfRange(A, i, A.length);
        if (A[i] < B[j]) {
            int[] rest = mergeHelper(A, B, i + 1, j);
            return concat(new int[]{A[i]}, rest);
        } else {
            int[] rest = mergeHelper(A, B, i, j + 1);
            return concat(new int[]{B[j]}, rest);
        }
    }

    public static int kthSmallest(int[] A, int k) {
        quickSort(A, 0, A.length - 1);
        return A[k - 1];
    }

    public static boolean subsetSum(int[] A, int target) {
        return subsetHelper(A, target, 0);
    }

    private static boolean subsetHelper(int[] A, int target, int i) {
        if (target == 0) return true;
        if (i >= A.length || target < 0) return false;
        return subsetHelper(A, target - A[i], i + 1) || subsetHelper(A, target, i + 1);
    }

    private static int[] concat(int[] first, int[] second) {
        int[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static void main(String[] args) {
        int[] A = {3, 1, 4, 2};
        quickSort(A, 0, A.length - 1);
        System.out.println("QuickSort: " + Arrays.toString(A));

        int[] merged = mergeSorted(new int[]{1, 3, 5}, new int[]{2, 4, 6});
        System.out.println("Merged: " + Arrays.toString(merged));

        System.out.println("3rd smallest: " + kthSmallest(new int[]{9, 5, 2, 7}, 3));

        System.out.println("Subset sum to 6: " + subsetSum(new int[]{3, 4, 5}, 6));
    }
}