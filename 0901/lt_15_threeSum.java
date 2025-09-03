class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> result;
        int n = nums.size();
        if (n < 3) return result;

        sort(nums.begin(), nums.end()); // 排序，方便去重和雙指標

        for (int i = 0; i < n - 2; i++) {
            // 避免重複的第一個元素
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.push_back({nums[i], nums[left], nums[right]});
                    // 移動 left 並跳過重複
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    // 移動 right 並跳過重複
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 總和太小，左指標右移
                } else {
                    right--; // 總和太大，右指標左移
                }
            }
        }

        return result;
    }

    /*
    解題思路：
    1. 將 nums 排序，便於去重和使用雙指標。
    2. 固定第一個元素 nums[i]，使用雙指標 left 和 right 遍歷剩餘元素。
    3. 計算 sum = nums[i] + nums[left] + nums[right]：
       - 若 sum == 0，加入結果，並跳過重複元素。
       - 若 sum < 0，左指標右移。
       - 若 sum > 0，右指標左移。
    4. 外層迴圈跳過重複的 nums[i]，避免重複三元組。
    時間複雜度：O(n^2)，排序 O(n log n) + 雙指標 O(n^2)。
    空間複雜度：O(1)，不計輸出使用額外空間。
    */
};