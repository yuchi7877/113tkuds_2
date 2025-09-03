class Solution {
public:
    int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            // 檢查是否溢位
            if (rev > INT_MAX/10 || (rev == INT_MAX/10 && pop > 7)) return 0;
            if (rev < INT_MIN/10 || (rev == INT_MIN/10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    /*
    解題思路：
    1. 使用 rev 保存反轉後的結果。
    2. 循環取 x 的末位 pop = x % 10，並更新 x /= 10。
    3. 在每次加入 pop 前，檢查 rev * 10 + pop 是否會溢出 32 位元整數範圍：
       - 正數溢位：rev > INT_MAX/10 或 rev == INT_MAX/10 且 pop > 7
       - 負數溢位：rev < INT_MIN/10 或 rev == INT_MIN/10 且 pop < -8
       - 溢位則返回 0
    4. 安全則 rev = rev * 10 + pop。
    時間複雜度：O(log10(x))，因為每次處理一位數字。
    空間複雜度：O(1)，不使用額外空間。
    */
};