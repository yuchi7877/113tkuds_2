class Solution {
public:
    int removeElement(vector<int>& nums, int val) {
        int slow = 0; // 慢指標指向下一個非 val 元素應該放置的位置
        for (int fast = 0; fast < nums.size(); fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow; // 返回不等於 val 的元素數量
    }

    /*
    解題思路：
    1. 使用雙指標 slow 和 fast：
       - slow 指向下一個非 val 元素應該放置的位置。
       - fast 遍歷整個陣列。
    2. 當 nums[fast] != val：
       - 將 nums[fast] 複製到 nums[slow]。
       - slow 前進一格。
    3. 遍歷完成後，slow 即為不等於 val 的元素數量。
    時間複雜度：O(n)，遍歷一次陣列。
    空間複雜度：O(1)，原地修改陣列。
    */
};