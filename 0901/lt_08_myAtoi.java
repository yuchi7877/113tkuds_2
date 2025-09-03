class Solution {
public:
    int myAtoi(string s) {
        int i = 0, n = s.size();
        // 忽略前導空格
        while (i < n && s[i] == ' ') i++;

        if (i == n) return 0;

        // 處理符號
        int sign = 1;
        if (s[i] == '+' || s[i] == '-') {
            sign = (s[i] == '-') ? -1 : 1;
            i++;
        }

        int result = 0;
        while (i < n && isdigit(s[i])) {
            int digit = s[i] - '0';
            // 檢查是否溢位
            if (result > (INT_MAX - digit) / 10) {
                return (sign == 1) ? INT_MAX : INT_MIN;
            }
            result = result * 10 + digit;
            i++;
        }

        return result * sign;
    }

    /*
    解題思路：
    1. 先跳過前導空格。
    2. 檢查符號，若有 '-' 則 sign = -1，'+' 或沒有符號則 sign = 1。
    3. 逐字元讀取數字，計算 result = result * 10 + digit。
    4. 在每次加入 digit 前，檢查是否會溢位：
       - 若 result > (INT_MAX - digit)/10，則溢位，返回 INT_MAX 或 INT_MIN。
    5. 最後返回 result * sign。
    時間複雜度：O(n)，n 為字串長度。
    空間複雜度：O(1)，不使用額外空間。
    */
};