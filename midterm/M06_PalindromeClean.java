import java.io.*;

/**
 * M06_PalindromeClean.java
 *
 * 輸入：
 *   一行字串
 * 輸出：
 *   Yes / No
 *
 * 範例：
 *   Input : A man, a plan, a canal: Panama
 *   Output: Yes
 */
public class M06_PalindromeClean {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        if (isPalindrome(s)) System.out.println("Yes");
        else System.out.println("No");
    }

    // 使用雙指標檢查是否回文
    static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);

            if (!Character.isLetter(ci)) { i++; continue; }
            if (!Character.isLetter(cj)) { j--; continue; }

            // 統一成小寫比對
            ci = Character.toLowerCase(ci);
            cj = Character.toLowerCase(cj);

            if (ci != cj) return false;
            i++;
            j--;
        }
        return true;
    }
}

/*
複雜度：
- 時間：O(n)，每個字元最多檢查一次。
- 空間：O(1)，只用到雙指標。
*/