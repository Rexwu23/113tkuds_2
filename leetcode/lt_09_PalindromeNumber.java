class lt_09_PalindromeNumber {
    public boolean isPalindrome(int x) {
        // 負數、結尾是0但不是0本身，都不可能是迴文
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversed = 0;
        // 只需要反轉一半，避免溢出
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // 偶數長度: x == reversed
        // 奇數長度: x == reversed/10 (去掉中間那一位)
        return x == reversed || x == reversed / 10;
    }
}
