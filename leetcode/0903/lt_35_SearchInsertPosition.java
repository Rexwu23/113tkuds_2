class Solution {
    public int searchInsert(int[] nums, int target) {
        int l = 0, r = nums.length;          // search in [l, r)
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= target) r = m;    // move left to find first >= target
            else l = m + 1;
        }
        return l;                            // l is insert position (or exact index)
    }
}
