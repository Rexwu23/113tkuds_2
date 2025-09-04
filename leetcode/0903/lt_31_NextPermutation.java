class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        // 1) 從右找第一個「升序拐點」i，使 nums[i] < nums[i+1]
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        if (i >= 0) {
            // 2) 從右找第一個大於 nums[i] 的 j，交換 i 與 j
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        // 3) 將 i 右側的序列反轉成遞增，得到最小後綴
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }

    private void reverse(int[] a, int l, int r) {
        while (l < r) swap(a, l++, r--);
    }
}
