class Solution {
public:
    vector<int> findSubstring(string s, vector<string>& words) {
        vector<int> res;
        if (words.empty() || s.empty()) return res;

        int wordLen = words[0].size();
        int wordCount = words.size();
        int totalLen = wordLen * wordCount;
        if (s.size() < totalLen) return res;

        unordered_map<string, int> wordMap;
        for (auto &w : words) wordMap[w]++;

        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            unordered_map<string, int> window;
            for (int j = i; j + wordLen <= s.size(); j += wordLen) {
                string str = s.substr(j, wordLen);
                if (wordMap.count(str)) {
                    window[str]++;
                    count++;
                    while (window[str] > wordMap[str]) {
                        string leftWord = s.substr(left, wordLen);
                        window[leftWord]--;
                        left += wordLen;
                        count--;
                    }
                    if (count == wordCount) res.push_back(left);
                } else {
                    window.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }
        return res;
    }

    /*
    解題思路：
    1. 每個單詞長度相同，使用滑動窗口遍歷 s：
       - i 從 0 到 wordLen - 1，保證檢查所有可能的對齊方式。
    2. 用 unordered_map 記錄 words 中每個單詞出現次數。
    3. 窗口內用另一個 map 記錄當前單詞出現次數：
       - 當某單詞出現次數超過 words 中的次數，左邊界右移並更新窗口。
       - 當窗口內單詞數等於 words.size()，記錄 left 為起始索引。
    4. 若遇到不在 words 中的單詞，清空窗口並移動 left。
    時間複雜度：O(wordLen * n)，n 為 s 長度。
    空間複雜度：O(m)，m 為 words.size()。
    */
};