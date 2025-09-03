class Solution {
public:
    int romanToInt(string s) {
        unordered_map<char, int> roman = {
            {'I', 1}, {'V', 5}, {'X', 10}, {'L', 50},
            {'C', 100}, {'D', 500}, {'M', 1000}
        };

        int total = 0;
        for (int i = 0; i < s.size(); i++) {
            // 若當前字符小於下一個字符，則使用減法
            if (i + 1 < s.size() && roman[s[i]] < roman[s[i + 1]]) {
                total -= roman[s[i]];
            } else {
                total += roman[s[i]];
            }
        }

        return total;
    }

    /*
    解題思路：
    1. 建立哈希表 roman，儲存羅馬字符對應的數值。
    2. 從左到右遍歷字串 s：
       - 若當前字符值小於下一個字符值，則 total 減去當前字符（減法情況）。
       - 否則 total 加上當前字符值。
    3. 返回 total 作為整數結果。
    時間複雜度：O(n)，n = s.length()。
    空間複雜度：O(1)，哈希表大小固定。
    */
};