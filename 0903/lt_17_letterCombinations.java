class Solution {
public:
    vector<string> letterCombinations(string digits) {
        vector<string> result;
        if (digits.empty()) return result;

        // 數字到字母映射
        string mapping[10] = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        string current;
        backtrack(digits, 0, current, result, mapping);
        return result;
    }

private:
    void backtrack(const string& digits, int index, string& current, vector<string>& result, string mapping[]) {
        if (index == digits.size()) {
            result.push_back(current);
            return;
        }

        string letters = mapping[digits[index] - '0'];
        for (char c : letters) {
            current.push_back(c);
            backtrack(digits, index + 1, current, result, mapping);
            current.pop_back(); // 回溯
        }
    }

    /*
    解題思路：
    1. 建立數字到字母的映射。
    2. 使用回溯法（DFS）生成所有可能的組合：
       - current 保存當前組合。
       - 當 index == digits.size()，表示完成一組組合，加入結果。
       - 否則遍歷當前數字對應的字母，每次選一個字母加入 current，遞迴到下一位。
       - 遞迴返回後彈出最後一個字母，嘗試其他字母（回溯）。
    3. 最後返回 result。
    時間複雜度：O(3^n * 4^m)，n 為映射到 3 個字母的數字個數，m 為映射到 4 個字母的數字個數。
    空間複雜度：O(n)，遞迴深度為 digits 長度。
    */
};