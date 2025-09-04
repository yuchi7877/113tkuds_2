class Solution {
public:
    int longestValidParentheses(string s) {
        stack<int> st;
        st.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.size(); i++) {
            if (s[i] == '(') {
                st.push(i);
            } else {
                st.pop();
                if (st.empty()) {
                    st.push(i);
                } else {
                    maxLen = max(maxLen, i - st.top());
                }
            }
        }
        return maxLen;
    }
};

/*
解題思路：
1. 題目要求找出最長的「有效括號子字串」長度。
2. 使用 stack 來記錄括號的索引，初始化先放 -1 來標記基準點。
3. 遍歷字串：
   - 若遇到 '('，將索引壓入 stack。
   - 若遇到 ')'，pop 一個索引：
     - 若此時 stack 為空，將當前索引作為新的基準點壓入 stack。
     - 否則計算當前有效長度 = i - stack.top()，並更新最大值。
4. 時間複雜度 O(n)，因為每個元素最多進出 stack 一次。
5. 空間複雜度 O(n)，用於 stack 儲存索引。
*/
