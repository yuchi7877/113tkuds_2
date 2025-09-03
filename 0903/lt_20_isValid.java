class Solution {
public:
    bool isValid(string s) {
        stack<char> st;
        for (char c : s) {
            if (c == '(' || c == '[' || c == '{') {
                st.push(c); // 遇到左括號入棧
            } else {
                if (st.empty()) return false; // 沒有對應左括號
                char top = st.top();
                if ((c == ')' && top != '(') ||
                    (c == ']' && top != '[') ||
                    (c == '}' && top != '{')) {
                    return false; // 括號類型不匹配
                }
                st.pop(); // 匹配成功，彈出左括號
            }
        }
        return st.empty(); // 所有括號匹配完，棧應為空
    }

    /*
    解題思路：
    1. 使用堆疊 stack 存放左括號。
    2. 遍歷字串 s：
       - 遇到左括號 '('、'['、'{'，入棧。
       - 遇到右括號 ')'、']'、'}'：
         a. 若棧為空，表示無對應左括號，返回 false。
         b. 若棧頂元素不匹配當前右括號，返回 false。
         c. 匹配成功，彈出棧頂左括號。
    3. 遍歷結束後，若棧為空，所有括號匹配，返回 true；否則返回 false。
    時間複雜度：O(n)，遍歷一次字串。
    空間複雜度：O(n)，最壞情況所有字符都是左括號。
    */
};