class Solution {
public:
    vector<vector<int>> res;
    vector<int> path;

    void backtrack(vector<int>& candidates, int target, int start) {
        if (target == 0) {
            res.push_back(path);
            return;
        }

        for (int i = start; i < candidates.size(); i++) {
            if (i > start && candidates[i] == candidates[i-1]) continue;
            if (candidates[i] > target) break;

            path.push_back(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1);
            path.pop_back();
        }
    }

    vector<vector<int>> combinationSum2(vector<int>& candidates, int target) {
        sort(candidates.begin(), candidates.end());
        backtrack(candidates, target, 0);
        return res;
    }
};

/*
解題思路：
1. 本題要求找到所有不重複的組合，每個數字只能用一次。
2. 對 candidates 排序，方便後續去重與剪枝。
3. 使用回溯法：
   - 遍歷時若同一層出現相同數字，跳過以避免重複組合。
   - 若當前數字大於 target，直接中止該層迴圈。
   - 當 target == 0，將當前路徑加入答案。
4. 時間複雜度大約為 O(2^n)，透過排序與剪枝可以降低不必要的遞迴。
*/
