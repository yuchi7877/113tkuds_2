class Solution {
public:
    void solveSudoku(vector<vector<char>>& board) {
        backtrack(board);
    }
    
private:
    bool backtrack(vector<vector<char>>& board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (backtrack(board)) return true;
                            board[i][j] = '.';
                        }
                    }
                    return false; // 如果 1~9 都不行，回溯
                }
            }
        }
        return true; // 全部填滿
    }
    
    bool isValid(vector<vector<char>>& board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == c) return false;             // 檢查 row
            if (board[i][col] == c) return false;             // 檢查 col
            int subRow = 3 * (row / 3) + i / 3;
            int subCol = 3 * (col / 3) + i % 3;
            if (board[subRow][subCol] == c) return false;     // 檢查 3x3 box
        }
        return true;
    }
};

/*
解題思路：
1. 使用回溯法（DFS）：
   - 找到第一個空格 '.'，嘗試填入 1~9。
   - 每次填入前先檢查是否符合數獨規則（行、列、3x3 box）。
   - 若合法則繼續遞迴，否則回溯。
2. 若整個盤面都填滿，返回 true，代表找到解。
3. 題目保證唯一解，因此第一個成功解即可結束。
4. 時間複雜度：最壞情況接近 O(9^(81))，但由於限制多、剪枝效果大，實際運行很快。
5. 空間複雜度 O(1)，因為盤面固定 9x9。
*/
