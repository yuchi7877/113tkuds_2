class Solution {
public:
    int divide(int dividend, int divisor) {
        // 特殊溢出處理
        if (dividend == INT_MIN && divisor == -1) return INT_MAX;
        if (dividend == INT_MIN && divisor == 1) return INT_MIN;

        // 判斷結果符號
        bool negative = (dividend < 0) ^ (divisor < 0);

        // 使用 long 避免溢出，轉成正數處理
        long long dvd = abs((long long)dividend);
        long long dvs = abs((long long)divisor);

        int quotient = 0;
        while (dvd >= dvs) {
            long long temp = dvs, multiple = 1;
            while (dvd >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            dvd -= temp;
            quotient += multiple;
        }

        return negative ? -quotient : quotient;
    }

    /*
    解題思路：
    1. 使用位運算倍增法模擬除法：
       - 將 dividend 和 divisor 轉為正數。
       - 每次將 divisor 左移倍增，直到大於 dividend。
       - 減去最大倍增的 divisor，並累加倍增到 quotient。
    2. 處理符號：
       - XOR 判斷 dividend 與 divisor 是否異號，異號則結果為負。
    3. 特殊情況：
       - dividend = INT_MIN 且 divisor = -1，會溢出，返回 INT_MAX。
    時間複雜度：O(log n)^2，n 為 dividend 的絕對值。
    空間複雜度：O(1)，使用常數額外空間。
    */
};