class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        int n = nums.size();
        int i = n - 2;

        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums[i], nums[j]);
        }

        reverse(nums.begin() + i + 1, nums.end());
    }
};

/*
解題思路：
1. 題目要求找到「下一個字典序排列」。
2. 從右往左找到第一個下降點 nums[i]，這代表 nums[i] 還可以被替換成更大的數字。
3. 再從右往左找到第一個比 nums[i] 大的數字 nums[j]，將兩者交換。
4. 最後反轉 i 之後的子序列，讓整個排列盡可能小。
5. 時間複雜度 O(n)，只需一次掃描與反轉；空間複雜度 O(1)。
*/
