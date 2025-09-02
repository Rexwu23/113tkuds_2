public class lt_04_MedianofTwoSortedArrays {
   public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 讓 nums1 是較短的那個
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;
        int half = (m + n + 1) / 2; // 左半部總長度（含中位數）

        while (left <= right) {
            int i = (left + right) / 2;   // nums1 的切點
            int j = half - i;             // nums2 的切點

            int maxLeft1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int minRight1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int maxLeft2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int minRight2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // 已找到正確切分
                if (((m + n) & 1) == 1) {
                    // 奇數：左半邊最大值
                    return Math.max(maxLeft1, maxLeft2);
                } else {
                    // 偶數：左右邊界平均
                    int leftMax = Math.max(maxLeft1, maxLeft2);
                    int rightMin = Math.min(minRight1, minRight2);
                    return (leftMax + rightMin) / 2.0;
                }
            } else if (maxLeft1 > minRight2) {
                // i 太大，往左縮
                right = i - 1;
            } else {
                // i 太小，往右擴
                left = i + 1;
            }
        }
        // 正常不會到這裡
        return 0.0;
    }
}
