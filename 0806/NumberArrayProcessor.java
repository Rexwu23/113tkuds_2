import java.util.*;

public class NumberArrayProcessor {
    public static int[] removeDuplicates(int[] A) {
        return Arrays.stream(A).distinct().toArray();
    }

    public static int[] mergeSortedArrays(int[] A, int[] B) {
        int[] result = new int[A.length + B.length];
        int i = 0, j = 0, k = 0;
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) result[k++] = A[i++];
            else result[k++] = B[j++];
        }
        while (i < A.length) result[k++] = A[i++];
        while (j < B.length) result[k++] = B[j++];
        return result;
    }

    public static int findMostFrequent(int[] A) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : A) freq.put(num, freq.getOrDefault(num, 0) + 1);
        return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static int[][] splitEqualSum(int[] A) {
        int total = Arrays.stream(A).sum(), sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            if (sum >= total / 2) {
                return new int[][]{
                    Arrays.copyOfRange(A, 0, i + 1),
                    Arrays.copyOfRange(A, i + 1, A.length)
                };
            }
        }
        return new int[][]{A, {}};
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 2, 3, 4, 5, 1};
        int[] B = {2, 4, 6};

        System.out.println("去重: " + Arrays.toString(removeDuplicates(A)));
        System.out.println("合併: " + Arrays.toString(mergeSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6})));
        System.out.println("最多次: " + findMostFrequent(A));
        int[][] split = splitEqualSum(new int[]{1, 2, 3, 4, 5});
        System.out.println("切割: " + Arrays.toString(split[0]) + " / " + Arrays.toString(split[1]));
    }
}

