class Solution {
public:
    string countAndSay(int n) {
        if (n == 1) return "1";

        string prev = countAndSay(n - 1);
        string result = "";
        int count = 1;

        for (int i = 0; i < prev.size(); i++) {
            if (i + 1 < prev.size() && prev[i] == prev[i + 1]) {
                count++;
            } else {
                result += to_string(count) + prev[i];
                count = 1;
            }
        }

        return result;
    }
};

/*
解題思路：
1. 題目要求輸出 count-and-say 序列的第 n 項。
2. 使用遞迴計算前一項 countAndSay(n-1)。
3. 對前一項進行連續字符計數 (run-length encoding)。
4. 將每段連續字符的數量與字符組合成新字串，作為當前項返回。
5. 遞迴終止條件為 n == 1，直接返回 "1"。
*/
