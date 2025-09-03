class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> result;
        string current;
        backtrack(result, current, 0, 0, n);
        return result;
    }

private:
    void backtrack(vector<string>& result, string& current, int open, int close, int maxPairs) {
        if (current.size() == maxPairs * 2) {
            result.push_back(current);
            return;
        }

        // 可以放開括號
        if (open < maxPairs) {
            current.push_back('(');
            backtrack(result, current, open + 1, close, maxPairs);
            current.pop_back(); // 回溯
        }

        // 可以放閉括號
        if (close < open) {
            current.push_back(')');
            backtrack(result, current, open, close + 1, maxPairs);
            current.pop_back(); // 回溯
        }
    }

    /*
    解題思路：
    1. 使用回溯法 (DFS) 建立有效括號組合：
       - current 保存當前組合。
       - open 記錄已放的 '(' 數量，close 記錄已放的 ')' 數量。
       - 當 current 長度達到 2*n 時，將 current 加入結果。
    2. 遞迴規則：
       - 若 open < n，可以放 '('。
       - 若 close < open，可以放 ')'。
       - 遞迴後回溯（pop_back）。
    3. 最後返回 result。
    時間複雜度：O(4^n / sqrt(n))，Catalan 數量級。
    空間複雜度：O(n)，遞迴深度。
    */
};
