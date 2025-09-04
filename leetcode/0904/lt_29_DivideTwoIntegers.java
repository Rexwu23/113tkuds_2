class Solution {
    public int divide(int dividend, int divisor) {
        // 溢位特判：INT_MIN / -1 超出 32 位上界
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // 結果符號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 用 long 取絕對值，避免 INT_MIN 取絕對值溢位
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int ans = 0;
        // 每次找最大的 (b << shift) <= a，做減法並加上對應的 1<<shift
        while (a >= b) {
            long tmp = b;
            int shift = 0;
            while ((tmp << 1) <= a) {
                tmp <<= 1;
                shift++;
            }
            a -= tmp;
            ans += 1 << shift;
        }
        return negative ? -ans : ans;
    }
}
