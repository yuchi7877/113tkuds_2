class Solution {
public:
    int maxArea(vector<int>& height) {
        int left = 0, right = height.size() - 1;
        int maxWater = 0;

        while (left < right) {
            int h = min(height[left], height[right]);
            int w = right - left;
            maxWater = max(maxWater, h * w);

            // 移動較短的指標，因為容器高度受短邊限制
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }

    /*
    解題思路：
    1. 使用雙指標 left, right 分別指向陣列兩端。
    2. 計算當前容器面積：h = min(height[left], height[right]), w = right - left, area = h * w。
    3. 更新最大面積 maxWater。
    4. 移動較短的指標，因為面積受短邊限制，移動短邊可能找到更高的邊。
    5. 直到 left >= right，返回 maxWater。
    時間複雜度：O(n)，每個指標最多移動 n 次。
    空間複雜度：O(1)，僅使用常數額外空間。
    */
};