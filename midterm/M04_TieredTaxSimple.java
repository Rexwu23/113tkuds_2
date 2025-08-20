import java.io.*;

/**
 * M04_TieredTaxSimple.java
 * 依級距逐段累加計稅；最後輸出平均稅額（四捨五入為整數）。
 *
 * Input:
 *   n
 *   x1
 *   x2
 *   ...
 * Output:
 *   Tax: a1
 *   Tax: a2
 *   ...
 *   Average: avg
 */
public class M04_TieredTaxSimple {
    // 區間上界（含），單位 NTS；最後一段用 Long.MAX_VALUE 代表無上限
    static final long[] CAP = {120_000L, 500_000L, 1_000_000L, Long.MAX_VALUE};
    // 對應稅率（百分比）
    static final int[] RATE = {5, 12, 20, 30};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringBuilder out = new StringBuilder();
        long sum = 0;

        for (int i = 0; i < n; i++) {
            long income = Long.parseLong(br.readLine().trim());
            long tax = calcTax(income);
            sum += tax;
            out.append("Tax: ").append(tax).append('\n');
        }

        long avg = Math.round(sum / (double) n); // 平均稅額四捨五入
        out.append("Average: ").append(avg).append('\n');
        System.out.print(out.toString());
    }

    // 逐段累加稅額
    static long calcTax(long x) {
        long tax = 0, prev = 0;
        for (int i = 0; i < CAP.length && x > prev; i++) {
            long upper = CAP[i];
            long portion = Math.min(x, upper) - prev; // 本段課稅金額
            if (portion > 0) {
                tax += portion * RATE[i] / 100;       // 稅率以百分比表示
                prev = upper;
            }
        }
        return tax;
    }
}

/*
複雜度：
- 時間：O(n)（每筆收入只跑常數個級距）
- 空間：O(1)
*/