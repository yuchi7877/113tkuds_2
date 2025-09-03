class Solution {
public:
    bool isMatch(string s, string p) {
        int m = s.size(), n = p.size();
        vector<vector<bool>> dp(m + 1, vector<bool>(n + 1, false));

        dp[0][0] = true; // 空字串匹配空模式

        // 處理空字串與模式匹配，可能有 '*' 代表前一字符出現 0 次
        for (int j = 2; j <= n; j += 2) {
            if (p[j - 1] == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p[j - 1] == '.' || p[j - 1] == s[i - 1]) {
                    dp[i][j] = dp[i - 1][j - 1]; // 匹配當前字符
                } else if (p[j - 1] == '*') {
                    // '*' 表示零次或多次前一字符
                    dp[i][j] = dp[i][j - 2]; // 前一字符出現 0 次
                    if (p[j - 2] == '.' || p[j - 2] == s[i - 1]) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j]; // 前一字符出現 1 次或多次
                    }
                }
            }
        }

        return dp[m][n];
    }

    /*
    解題思路：
    1. 使用 dp[i][j] 表示 s[0..i-1] 是否匹配 p[0..j-1]。
    2. 初始化 dp[0][0] = true，空字串匹配空模式。
    3. 處理空字串與模式匹配時可能有 '*' 可以匹配零次。
    4. 動態規劃遞推：
       - 若 p[j-1] 為普通字符或 '.'，則 dp[i][j] = dp[i-1][j-1]
       - 若 p[j-1] 為 '*'，則：
           a. dp[i][j] = dp[i][j-2] （零次前一字符）
           b. 若前一字符匹配 s[i-1]，dp[i][j] |= dp[i-1][j] （一個或多個前一字符）
    5. 返回 dp[m][n] 表示整個字串是否匹配。
    時間複雜度：O(m*n)，m = s.length(), n = p.length()
    空間複雜度：O(m*n)
    */
};