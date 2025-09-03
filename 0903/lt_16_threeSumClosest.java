class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int closest = nums[0] + nums[1] + nums[2]; // 初始最接近和

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // 更新最接近的和
                if (abs(sum - target) < abs(closest - target)) {
                    closest = sum;
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    // 正好等於 target，直接返回
                    return sum;
                }
            }
        }

        return closest;
    }

    /*
    解題思路：
    1. 將 nums 排序，便於使用雙指標。
    2. 遍歷每個元素作為第一個數 nums[i]。
    3. 使用雙指標 left, right 指向 i+1 和 n-1，計算 sum = nums[i]+nums[left]+nums[right]。
       - 若 sum 更接近 target，更新 closest。
       - 若 sum < target，左指標右移增加 sum。
       - 若 sum > target，右指標左移減小 sum。
       - 若 sum == target，直接返回 sum。
    4. 最後返回 closest。
    時間複雜度：O(n^2)，排序 O(n log n) + 雙指標 O(n^2)。
    空間複雜度：O(1)，不計輸出使用額外空間。
    */
};