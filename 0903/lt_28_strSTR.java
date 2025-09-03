class Solution {
public:
    int strStr(string haystack, string needle) {
        int n = haystack.size();
        int m = needle.size();
        if (m == 0) return 0; // 空字串特殊情況

        for (int i = 0; i <= n - m; i++) {
            int j = 0;
            for (; j < m; j++) {
                if (haystack[i + j] != needle[j]) break;
            }
            if (j == m) return i; // 找到完整匹配
        }
        return -1; // 未找到
    }

    /*
    解題思路：
    1. 暴力匹配：
       - 遍歷 haystack，每個位置嘗試匹配 needle。
       - i 從 0 到 n - m，檢查 haystack[i:i+m] 是否等於 needle。
    2. 如果完全匹配，返回當前索引 i。
    3. 如果遍歷完成仍未匹配，返回 -1。
    時間複雜度：O(n*m)，n 為 haystack 長度，m 為 needle 長度。
    空間複雜度：O(1)，只使用常數額外空間。
    */
};