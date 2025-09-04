class Solution {
public:
    int search(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) return mid;
            
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1;
    }
};

/*
解題思路：
1. 陣列是排序過但可能旋轉，因此仍可用二分搜尋法，只是需要判斷哪一半是有序的。
2. 每次計算 mid：
   - 若左半邊有序（nums[left] <= nums[mid]）：
     - 檢查 target 是否落在 [nums[left], nums[mid]) 區間內。
   - 否則右半邊有序：
     - 檢查 target 是否落在 (nums[mid], nums[right]] 區間內。
3. 根據判斷縮小搜尋區間，直到找到 target 或 left > right。
4. 時間複雜度 O(log n)，空間複雜度 O(1)。
*/
