
public class AdvancedStringRecursion {

    public static void permute(String s) {
        permuteHelper("", s);
    }

    private static void permuteHelper(String prefix, String remaining) {
        if (remaining.isEmpty()) {
            System.out.println(prefix);
            return;
        }
        for (int i = 0; i < remaining.length(); i++) {
            permuteHelper(prefix + remaining.charAt(i),
                          remaining.substring(0, i) + remaining.substring(i + 1));
        }
    }

    public static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean firstMatch = (!text.isEmpty() &&
                              (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return match(text, pattern.substring(2)) ||
                   (firstMatch && match(text.substring(1), pattern));
        } else {
            return firstMatch && match(text.substring(1), pattern.substring(1));
        }
    }

    public static String removeDuplicates(String s) {
        return removeDupHelper(s, "");
    }

    private static String removeDupHelper(String s, String seen) {
        if (s.isEmpty()) return "";
        char c = s.charAt(0);
        if (seen.indexOf(c) >= 0)
            return removeDupHelper(s.substring(1), seen);
        return c + removeDupHelper(s.substring(1), seen + c);
    }

    public static void printAllSubstrings(String s) {
        substringHelper(s, 0, "");
    }

    private static void substringHelper(String s, int i, String curr) {
        if (i == s.length()) {
            if (!curr.isEmpty()) System.out.println(curr);
            return;
        }
        substringHelper(s, i + 1, curr + s.charAt(i)); // include
        substringHelper(s, i + 1, curr);               // exclude
    }

    public static void main(String[] args) {
        System.out.println("Permutations of 'abc':");
        permute("abc");

        System.out.println("Match 'aab' with 'c*a*b': " + match("aab", "c*a*b"));

        System.out.println("Remove duplicates: " + removeDuplicates("banana"));

        System.out.println("All substrings of 'abc':");
        printAllSubstrings("abc");
    }
}
