class Solution {
public:
    vector<vector<int>> res; // 儲存所有結果組合
    vector<int> path;        // 當前遞迴路徑

    void backtrack(vector<int>& candidates, int target, int start) {
        if (target == 0) {  
            // 當 target == 0，代表找到一組組合
            res.push_back(path);
            return;
        }
        if (target < 0) return; // 超過 target 就不再繼續

        for (int i = start; i < candidates.size(); i++) {
            path.push_back(candidates[i]); // 選擇一個數字
            // 因為同一個數字可以重複使用，所以遞迴時 i 不要 +1
            backtrack(candidates, target - candidates[i], i); 
            path.pop_back(); // 撤銷選擇，回溯
        }
    }

    vector<vector<int>> combinationSum(vector<int>& candidates, int target) {
        backtrack(candidates, target, 0);
        return res;
    }
};

/*
解題思路：
1. 題目要求從 candidates 中找到所有數字組合，使其和等於 target。
2. 使用「回溯法 (Backtracking)」逐步嘗試每個數字。
   - 每次遞迴時，選擇一個數字加入 path，並減少 target。
   - 若 target == 0，代表找到一組組合，存入結果。
   - 若 target < 0，提前停止，不再繼續。
3. 因為同一個數字可以重複使用，所以遞迴時參數仍傳 i (而不是 i+1)。
4. 為了避免重複組合，我們控制迴圈起始位置從 start 開始。
5. 時間複雜度大約為 O(2^n)，但題目限制結果數量 < 150，所以可以接受。
*/
