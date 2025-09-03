class Solution {
public:
    string longestPalindrome(string s) {
        if (s.empty()) return "";

        int start = 0, maxLen = 0;

        for (int i = 0; i < s.size(); i++) {
            // 奇數長回文，以 i 為中心
            int len1 = expandAroundCenter(s, i, i);
            // 偶數長回文，以 i 和 i+1 為中心
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = max(len1, len2);

            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2; // 計算起始索引
            }
        }

        return s.substr(start, maxLen);
    }

private:
    // 從左右兩個中心向外擴展，返回回文長度
    int expandAroundCenter(const string &s, int left, int right) {
        while (left >= 0 && right < s.size() && s[left] == s[right]) {
            left--;
            right++;
        }
        return right - left - 1; // 擴展後長度 = right-left-1
    }

    /*
    解題思路：
    1. 使用中心擴展法：每個字符作為回文中心（奇數長），每對相鄰字符作為中心（偶數長）。
    2. 從中心向外擴展，檢查左右字符是否相等，直到不相等或超出邊界。
    3. 每次擴展計算回文長度，更新最大長度 maxLen 和起始索引 start。
    4. 最後使用 substr 返回最長回文子串。
    時間複雜度：O(n^2)，n 為字串長度。
    空間複雜度：O(1)，不使用額外空間。
    */
};