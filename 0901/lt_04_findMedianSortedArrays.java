class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        if (nums1.size() > nums2.size())
            return findMedianSortedArrays(nums2, nums1);

        int m = nums1.size();
        int n = nums2.size();
        int left = 0, right = m;
        int medianPos = (m + n + 1) / 2;

        while (left <= right) {
            int i = (left + right) / 2;  
            int j = medianPos - i;      

            int nums1LeftMax = (i == 0) ? INT_MIN : nums1[i - 1];
            int nums1RightMin = (i == m) ? INT_MAX : nums1[i];

            int nums2LeftMax = (j == 0) ? INT_MIN : nums2[j - 1];
            int nums2RightMin = (j == n) ? INT_MAX : nums2[j];

            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 找到正確分割
                if ((m + n) % 2 == 0)
                    return (max(nums1LeftMax, nums2LeftMax) + min(nums1RightMin, nums2RightMin)) / 2.0;
                else
                    return max(nums1LeftMax, nums2LeftMax);
            } else if (nums1LeftMax > nums2RightMin) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }

        throw invalid_argument("Input arrays are not sorted or invalid.");
    }

    /*
    解題思路：
    1. 對較短的數組做二分搜尋以優化效率。
    2. 將 nums1 和 nums2 分割成左右兩部分，使左半部分的元素都 <= 右半部分。
    3. 二分搜尋 nums1 的分割位置 i，對應 nums2 的分割位置 j = (m+n+1)/2 - i。
    4. 檢查條件：
       - nums1LeftMax <= nums2RightMin
       - nums2LeftMax <= nums1RightMin
       若成立即找到正確分割。
    5. 計算中位數：
       - 總長度為奇數時取左半最大值
       - 總長度為偶數時取左右最大最小值平均
    時間複雜度：O(log(min(m,n))) ≤ O(log(m+n))
    空間複雜度：O(1)
    */
};