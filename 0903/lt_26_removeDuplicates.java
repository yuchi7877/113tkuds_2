class Solution {
public:
    int removeDuplicates(vector<int>& nums) {
        if (nums.empty()) return 0;

        int slow = 0; // 慢指標指向最後一個唯一元素的位置
        for (int fast = 1; fast < nums.size(); fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast]; // 更新下一個唯一元素位置
            }
        }
        return slow + 1; // 唯一元素數量
    }

    /*
    解題思路：
    1. 使用雙指標 slow 和 fast：
       - slow 指向最後一個唯一元素。
       - fast 遍歷整個陣列。
    2. 當 nums[fast] != nums[slow]：
       - slow 前進一格，並將 nums[fast] 複製到 nums[slow]。
    3. 最後返回 slow + 1 作為唯一元素個數。
    時間複雜度：O(n)，遍歷一次陣列。
    空間複雜度：O(1)，原地修改陣列。
    */
};