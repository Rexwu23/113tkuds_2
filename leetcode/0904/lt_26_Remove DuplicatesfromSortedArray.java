class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int k = 1; // 已確認的唯一元素數量，同時是下一個寫入位置
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[k - 1]) {
                nums[k++] = nums[i];
            }
        }
        return k;
    }
}
