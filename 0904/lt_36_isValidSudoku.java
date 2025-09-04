class Solution {
public:
    bool isValidSudoku(vector<vector<char>>& board) {
        // 檢查 row
        for (int i = 0; i < 9; i++) {
            vector<bool> seen(9, false);
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    if (seen[num]) return false;
                    seen[num] = true;
                }
            }
        }
        
        // 檢查 col
        for (int j = 0; j < 9; j++) {
            vector<bool> seen(9, false);
            for (int i = 0; i < 9; i++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    if (seen[num]) return false;
                    seen[num] = true;
                }
            }
        }
        
        // 檢查 3x3 sub-box
        for (int boxRow = 0; boxRow < 3; boxRow++) {
            for (int boxCol = 0; boxCol < 3; boxCol++) {
                vector<bool> seen(9, false);
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        char c = board[boxRow * 3 + i][boxCol * 3 + j];
                        if (c != '.') {
                            int num = c - '1';
                            if (seen[num]) return false;
                            seen[num] = true;
                        }
                    }
                }
            }
        }
        
        return true;
    }
};

/*
解題思路：
1. 依序檢查 3 種限制：
   - 每一列 row：不得有重複數字。
   - 每一行 col：不得有重複數字。
   - 每個 3x3 子宮格：不得有重複數字。
2. 遍歷時忽略 '.'，只檢查數字 1~9。
3. 透過 vector<bool> (大小 9) 紀錄是否出現過，若已出現則立即回傳 false。
4. 若全部檢查通過，回傳 true。
5. 時間複雜度 O(81)，空間複雜度 O(1)，因為盤面大小固定 9x9。
*/
