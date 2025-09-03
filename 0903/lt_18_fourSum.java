class Solution {
public:
    vector<vector<int>> fourSum(vector<int>& nums, int target) {
        vector<vector<int>> result;
        int n = nums.size();
        if (n < 4) return result;

        sort(nums.begin(), nums.end());

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重第一個數
            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue; // 去重第二個數

                int left = j + 1, right = n - 1;
                while (left < right) {
                    long long sum = (long long)nums[i] + nums[j] + nums[left] + nums[right]; // 防止溢位
                    if (sum == target) {
                        result.push_back({nums[i], nums[j], nums[left], nums[right]});
                        while (left < right && nums[left] == nums[left + 1]) left++;   // 去重第三個數
                        while (left < right && nums[right] == nums[right - 1]) right--; // 去重第四個數
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    /*
    解題思路：
    1. 將 nums 排序，方便去重與使用雙指標。
    2. 固定前兩個數 nums[i] 和 nums[j]。
    3. 對剩餘元素使用雙指標 left 和 right 計算四數和：
       - 若 sum == target，加入結果，並跳過重複元素。
       - 若 sum < target，左指標右移。
       - 若 sum > target，右指標左移。
    4. 外層迴圈跳過重複的 nums[i] 和 nums[j]，避免重複四元組。
    5. 最後返回 result。
    時間複雜度：O(n^3)，三重迴圈 + 雙指標。
    空間複雜度：O(1)，不計輸出使用額外空間。
    */
};