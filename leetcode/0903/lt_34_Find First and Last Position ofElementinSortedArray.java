class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int left = lowerBound(nums, target);
        if (left == n || nums[left] != target) return new int[]{-1, -1};
        int right = upperBound(nums, target) - 1;
        return new int[]{left, right};
    }

    // first index i such that nums[i] >= x
    private int lowerBound(int[] a, int x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= x) r = m;
            else l = m + 1;
        }
        return l;
    }

    // first index i such that nums[i] > x
    private int upperBound(int[] a, int x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] > x) r = m;
            else l = m + 1;
        }
        return l;
    }
}
