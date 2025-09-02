public class lt_07_ReverseInteger {
   public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;     // 取最後一位
            x /= 10;              // 去掉最後一位

            // 檢查溢出（正數與負數分開檢查）
            if (rev > Integer.MAX_VALUE / 10 || 
                (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            if (rev < Integer.MIN_VALUE / 10 || 
                (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }

            rev = rev * 10 + pop; // 更新結果
        }
        return rev;
    }
}