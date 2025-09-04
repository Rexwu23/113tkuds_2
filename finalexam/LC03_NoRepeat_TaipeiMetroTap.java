// 檔名：LC03_NoRepeat_TaipeiMetroTap.java
// 讀取：一行字串 s
// 輸出：最長「無重複字元」子段長度

import java.io.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) { System.out.println(0); return; }

        // 以 ASCII 可見字元為主，可直接開 256
        int[] last = new int[256];
        for (int i = 0; i < 256; i++) last[i] = -1;

        int left = 0, ans = 0; // 目前視窗 [left..i]
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (last[c] >= left) left = last[c] + 1; // 碰到重複，縮左界
            last[c] = i;
            int len = i - left + 1;
            if (len > ans) ans = len;
        }
        System.out.println(ans);
    }
}
