class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        unordered_set<char> set; // 用來儲存當前窗口的字符
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.size(); right++) {
            char c = s[right];
            // 若右指標字符已存在於 set，左指標向右移動並刪除字符，直到沒有重複
            while (set.count(c)) {
                set.erase(s[left]);
                left++;
            }
            set.insert(c); // 加入右指標字符
            maxLen = max(maxLen, right - left + 1); // 更新最大長度
        }

        return maxLen;
    }

    /*
    解題思路：
    1. 使用滑動窗口（雙指標）追蹤當前不重複子字串。
    2. 使用 unordered_set 儲存窗口內的字符，方便 O(1) 查詢重複。
    3. 右指標逐步向右移動：
       - 若字符已在 set 中，左指標右移並移除字符，直到窗口不再重複。
    4. 每次移動右指標時計算窗口長度，更新最大值 maxLen。
    時間複雜度：O(n)，每個字符最多被加入和移除一次。
    空間複雜度：O(min(n, m))，m 為字符集大小（ASCII 約 128）。
    */
};

