class Solution {
public:
    string convert(string s, int numRows) {
        if (numRows == 1 || s.size() <= numRows) return s;

        vector<string> rows(min(numRows, (int)s.size()));
        int currRow = 0;
        bool goingDown = false;

        for (char c : s) {
            rows[currRow] += c;
            // 到頂或到底時改變方向
            if (currRow == 0 || currRow == numRows - 1) goingDown = !goingDown;
            currRow += goingDown ? 1 : -1;
        }

        string result;
        for (string &row : rows) {
            result += row;
        }
        return result;
    }

    /*
    解題思路：
    1. 使用一個 vector<string> 保存每一行的字符。
    2. 遍歷原字串，每個字符放到對應行：
       - 使用 currRow 記錄當前行。
       - 使用 goingDown 控制方向（向下或向上）。
    3. 當到達第一行或最後一行時，反轉方向。
    4. 最後將所有行合併成結果字串。
    時間複雜度：O(n)，n 為字串長度。
    空間複雜度：O(n)，存放所有字符。
    */
};