public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = (n - 2) / 2; i >= 0; i--) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反位置: " + left);
                return false;
            }
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反位置: " + right);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] tests = {
            {100, 90, 80, 70, 60, 75, 65},
            {100, 90, 80, 95, 60, 75, 65},
            {50},
            {}
        };

        for (int[] arr : tests) {
            boolean valid = isValidMaxHeap(arr);
            System.out.println("Result: " + valid);
        }
    }
}