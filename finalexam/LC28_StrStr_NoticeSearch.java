// 檔名：LC28_StrStr_NoticeSearch.java
// 讀取：兩行字串 -> haystack、needle
// 輸出：needle 在 haystack 中首次出現的起始索引；不存在則輸出 -1
// 規則：若 needle 長度為 0，回傳 0
//
// 解法：KMP（時間 O(n+m)，空間 O(m)）

import java.io.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String haystack = br.readLine();
        String needle   = br.readLine();

        if (haystack == null) haystack = "";
        if (needle == null) needle = "";

        System.out.println(indexOfKMP(haystack, needle));
    }

    private static int indexOfKMP(String s, String p) {
        int n = s.length(), m = p.length();
        if (m == 0) return 0;
        if (m > n) return -1;

        int[] pi = buildPi(p);
        int j = 0; // 指向 p
        for (int i = 0; i < n; i++) { // 指向 s
            while (j > 0 && s.charAt(i) != p.charAt(j)) j = pi[j - 1];
            if (s.charAt(i) == p.charAt(j)) j++;
            if (j == m) return i - m + 1;
        }
        return -1;
    }

    // 前綴函數：pi[k] = 以 k 結尾的最長「真前綴=後綴」長度
    private static int[] buildPi(String p) {
        int m = p.length();
        int[] pi = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && p.charAt(i) != p.charAt(j)) j = pi[j - 1];
            if (p.charAt(i) == p.charAt(j)) j++;
            pi[i] = j;
        }
        return pi;
    }
}
