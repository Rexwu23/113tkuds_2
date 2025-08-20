import java.io.*;
import java.util.*;

/**
 * M05_GCD_LCM_Recursive.java
 *
 * 輸入：兩個正整數 a, b
 * 輸出：GCD 與 LCM
 *
 * 範例：
 *   Input:  12 18
 *   Output: GCD: 6
 *           LCM: 36
 */
public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        long g = gcd(a, b);
        long l = (a / g) * b;   // 先除再乘避免溢位

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    // 遞迴輾轉相除法
    static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}

/*
複雜度：
- 時間：O(log(min(a, b)))，每次遞迴 mod 會縮小數字
- 空間：O(log(min(a, b)))（遞迴堆疊深度）
*/