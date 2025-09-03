class Solution {
public:
    bool isPalindrome(int x) {
        // 負數和末位為 0（且不為 0）一定不是回文
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversedHalf = 0;
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // 對於奇數長度，去掉中間位
        return x == reversedHalf || x == reversedHalf / 10;
    }

    /*
    解題思路：
    1. 負數或末位為 0（且不是 0 本身）的數字不是回文，直接返回 false。
    2. 反轉數字的一半：
       - 使用 reversedHalf = reversedHalf * 10 + x % 10
       - 同時 x /= 10
       - 當 x <= reversedHalf 時停止
    3. 判斷是否回文：
       - 偶數長度：x == reversedHalf
       - 奇數長度：x == reversedHalf / 10 （去掉中間位）
    4. 時間複雜度：O(log10(x))，每次處理一位數字。
    5. 空間複雜度：O(1)，不使用額外空間。
    */
};