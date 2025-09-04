class Solution {
public:
    int searchInsert(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return left;
    }
};

/*
解題思路：
1. 使用二分搜尋法尋找 target：
   - 若 nums[mid] == target，直接回傳 mid。
   - 若 nums[mid] < target，代表 target 應在右半邊，left = mid + 1。
   - 若 nums[mid] > target，代表 target 應在左半邊，right = mid - 1。
2. 當迴圈結束時，left 會停在「target 應插入的位置」。
3. 時間複雜度 O(log n)，空間複雜度 O(1)。
*/
