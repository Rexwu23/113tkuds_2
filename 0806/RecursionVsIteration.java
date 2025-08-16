public class RecursionVsIteration {

    public static int binomialRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRec(n - 1, k - 1) + binomialRec(n - 1, k);
    }

    public static int binomialIter(int n, int k) {
        int[][] C = new int[n+1][k+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) C[i][j] = 1;
                else C[i][j] = C[i-1][j-1] + C[i-1][j];
            }
        }
        return C[n][k];
    }

    public static int productRec(int[] A, int i) {
        if (i == A.length) return 1;
        return A[i] * productRec(A, i + 1);
    }

    public static int productIter(int[] A) {
        int prod = 1;
        for (int x : A) prod *= x;
        return prod;
    }

    public static int vowelCountRec(String s) {
        if (s.isEmpty()) return 0;
        char c = Character.toLowerCase(s.charAt(0));
        int count = "aeiou".indexOf(c) >= 0 ? 1 : 0;
        return count + vowelCountRec(s.substring(1));
    }

    public static int vowelCountIter(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray())
            if ("aeiou".indexOf(c) >= 0) count++;
        return count;
    }

    public static boolean bracketMatchRec(String s, int i, int balance) {
        if (i == s.length()) return balance == 0;
        if (balance < 0) return false;
        char c = s.charAt(i);
        if (c == '(') return bracketMatchRec(s, i + 1, balance + 1);
        if (c == ')') return bracketMatchRec(s, i + 1, balance - 1);
        return bracketMatchRec(s, i + 1, balance);
    }

    public static boolean bracketMatchIter(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') balance++;
            else if (c == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    public static void main(String[] args) {
        System.out.println("Binomial (Rec): " + binomialRec(5, 2));
        System.out.println("Binomial (Iter): " + binomialIter(5, 2));

        int[] arr = {2, 3, 4};
        System.out.println("Product (Rec): " + productRec(arr, 0));
        System.out.println("Product (Iter): " + productIter(arr));

        System.out.println("Vowels (Rec): " + vowelCountRec("Recursion"));
        System.out.println("Vowels (Iter): " + vowelCountIter("Recursion"));

        System.out.println("Brackets (Rec): " + bracketMatchRec("(())()", 0, 0));
        System.out.println("Brackets (Iter): " + bracketMatchIter("(())()"));
    }
}
