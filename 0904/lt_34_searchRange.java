class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        int first = findFirst(nums, target);
        int last = findLast(nums, target);
        return {first, last};
    }
    
private:
    int findFirst(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                if (nums[mid] == target) ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    
    int findLast(vector<int>& nums, int target) {
        int left = 0, right = nums.size() - 1, ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target) ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
};

/*
解題思路：
1. 陣列已排序，可用二分搜尋法 O(log n) 找目標值範圍。
2. 用兩次 binary search：
   - 第一次找 target 的最左邊索引（first）。
   - 第二次找 target 的最右邊索引（last）。
3. 若 target 不存在，回傳 [-1, -1]。
4. 時間複雜度 O(log n)，空間複雜度 O(1)。
*/
