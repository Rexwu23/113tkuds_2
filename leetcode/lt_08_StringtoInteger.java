public class lt_08_StringtoInteger {
   public int myAtoi(String s) {
        int n = s.length(), i = 0;

        // 1) 跳過前導空白
        while (i < n && s.charAt(i) == ' ') i++;

        // 2) 處理正負號
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3) 讀取數字並檢查溢出
        int res = 0;
        while (i < n && Character.isDigit(s.charAt(i))) {
            int d = s.charAt(i) - '0';

            // 若再乘10加 d 會溢出，就直接夾到邊界
            if (res > Integer.MAX_VALUE / 10 ||
               (res == Integer.MAX_VALUE / 10 && d > (sign == 1 ? 7 : 8))) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + d;
            i++;
        }

        // 4) 套用正負號
        return res * sign;
    }
}
