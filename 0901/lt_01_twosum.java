class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        unordered_map<int, int> map; // 儲存「數值 → 索引」

        for (int i = 0; i < nums.size(); i++) {
            int complement = target - nums[i]; // 需要的另一個數
            if (map.count(complement)) {
                return {map[complement], i}; // 找到答案，返回索引
            }
            map[nums[i]] = i; // 將當前數值加入 map
        }

        return {}; // 保險寫法，理論上不會到這裡
    }

    /*
    解題思路：
    1. 使用 HashMap 儲存數值及其索引。
    2. 遍歷 nums，對每個元素計算 complement = target - nums[i]。
    3. 檢查 map 中是否存在 complement：
       - 若存在，返回 complement 的索引和當前索引。
       - 若不存在，將當前數加入 map。
    4. 保證每個元素只使用一次。
    時間複雜度：O(n)，每個元素只遍歷一次。
    空間複雜度：O(n)，用於存放 HashMap。
    */
};