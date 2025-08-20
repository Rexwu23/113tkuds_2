import java.io.*;
import java.util.*;

/**
 * M10_RBPropertiesCheck.java
 *
 * 讀入：
 *   n
 *   v1 color1 v2 color2 ... vn colorn
 *   （vi = -1 代表 null；null 的顏色視為黑）
 *
 * 輸出（擇一）：
 *   RB Valid
 *   RootNotBlack
 *   RedRedViolation at index i
 *   BlackHeightMismatch
 */
public class M10_RBPropertiesCheck {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] val = new int[n];
        char[] col = new char[n];

        // 讀 n 組 (value, color)
        StringTokenizer st = new StringTokenizer(readAllTokens(br));
        for (int i = 0; i < n; i++) {
            val[i] = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            col[i] = (c == null || c.isEmpty()) ? 'B' : Character.toUpperCase(c.charAt(0));
            if (val[i] == -1) col[i] = 'B'; // null 視為黑
        }

        // 1) 根為黑
        if (n > 0 && val[0] != -1 && col[0] == 'R') {
            System.out.println("RootNotBlack");
            return;
        }

        // 2) 不得有相鄰紅節點（檢查每個紅節點的左右子）
        for (int i = 0; i < n; i++) {
            if (i >= n || val[i] == -1) continue;
            if (col[i] == 'R') {
                int l = 2 * i + 1, r = 2 * i + 2;
                if (l < n && val[l] != -1 && col[l] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
                if (r < n && val[r] != -1 && col[r] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        // 3) 黑高一致（任何節點到 NIL 的黑節點數相等）
        if (blackHeight(val, col, 0, n) == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    // 將剩餘輸入整段讀完（方便跨行 token）
    private static String readAllTokens(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (String line; (line = br.readLine()) != null && line.trim().length() > 0; ) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(line.trim());
            if (sb.length() > 2_000_000) break; // 安全保障
        }
        return sb.toString();
    }

    /**
     * 回傳以 idx 為根的黑高；若左右不一致回傳 -1 作為違規訊號。
     * 規則：
     *   - idx 越界或 val[idx] == -1 視為 NIL，黑高 = 1
     *   - 非 NIL 的黑節點黑高 +1，紅節點不加
     */
    private static int blackHeight(int[] val, char[] col, int idx, int n) {
        if (idx >= n || val[idx] == -1) return 1; // NIL (Black)

        int l = blackHeight(val, col, 2 * idx + 1, n);
        if (l == -1) return -1;
        int r = blackHeight(val, col, 2 * idx + 2, n);
        if (r == -1) return -1;

        if (l != r) return -1; // 左右黑高不一致

        return l + (col[idx] == 'B' ? 1 : 0);
    }
}

/*
複雜度：
- 時間：O(n) — 單次線性掃描與一次 DFS。
- 空間：O(h) — 遞迴堆疊（h 為樹高）；陣列本身 O(n) 由輸入提供。
*/