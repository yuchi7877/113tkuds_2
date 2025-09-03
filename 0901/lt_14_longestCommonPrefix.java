class Solution {
public:
    string longestCommonPrefix(vector<string>& strs) {
        if (strs.empty()) return "";

        // 使用第一個字串作為基準
        string prefix = strs[0];

        for (int i = 1; i < strs.size(); i++) {
            int j = 0;
            // 比對 prefix 與當前字串的相同部分
            while (j < prefix.size() && j < strs[i].size() && prefix[j] == strs[i][j]) {
                j++;
            }
            // 更新 prefix 為共同前綴
            prefix = prefix.substr(0, j);
            if (prefix.empty()) return ""; // 早期退出
        }

        return prefix;
    }

    /*
    解題思路：
    1. 如果 strs 為空，返回空字串。
    2. 將第一個字串作為基準 prefix。
    3. 逐個比對剩餘字串：
       - 找到 prefix 與當前字串的最大公共前綴長度 j。
       - 更新 prefix = prefix.substr(0, j)。
       - 若 prefix 為空，表示沒有公共前綴，直接返回 ""。
    4. 最後返回 prefix。
    時間複雜度：O(S)，S = 所有字串字符總數，因為最壞情況每個字符都要比對。
    空間複雜度：O(1)，使用常數額外空間（不計算輸出）。
    */
};