class Solution {
public:
    string intToRoman(int num) {
        // 羅馬數字與對應數值，從大到小
        vector<int> values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        vector<string> symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        string result;

        for (int i = 0; i < values.size(); i++) {
            while (num >= values[i]) {
                num -= values[i];
                result += symbols[i];
            }
        }

        return result;
    }

    /*
    解題思路：
    1. 建立從大到小的羅馬數字符號與其對應數值。
    2. 使用貪心法：
       - 從最大的數值開始，當 num >= values[i] 時，減去 values[i] 並在結果字串添加 symbols[i]。
       - 重複直到 num < values[i]。
    3. 循環遍歷所有面值直到 num 減至 0。
    4. 返回組合完成的羅馬數字字串。
    時間複雜度：O(1)，因為數值最大為 3999，迴圈固定。
    空間複雜度：O(1)，只使用常數額外空間。
    */
};