public class RecursiveMathCalculator {

    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static int catalan(int n) {
        if (n <= 1) return 1;
        int res = 0;
        for (int i = 0; i < n; i++)
            res += catalan(i) * catalan(n - 1 - i);
        return res;
    }

    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    public static boolean isPalindrome(int num) {
        return isPalindromeHelper(Integer.toString(num));
    }

    private static boolean isPalindromeHelper(String s) {
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return isPalindromeHelper(s.substring(1, s.length() - 1));
    }

    public static void main(String[] args) {
        System.out.println("C(5, 2) = " + combination(5, 2));
        System.out.println("Catalan(4) = " + catalan(4));
        System.out.println("Hanoi(3) moves = " + hanoiMoves(3));
        System.out.println("Is 12321 palindrome? " + isPalindrome(12321));
    }
}
