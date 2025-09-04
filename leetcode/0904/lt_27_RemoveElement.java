class Solution {
    public int removeElement(int[] nums, int val) {
        int k = 0;                       // 下一個寫入位置
        for (int x : nums) {
            if (x != val) nums[k++] = x; // 保留的元素搬到前面
        }
        return k;
    }
}
