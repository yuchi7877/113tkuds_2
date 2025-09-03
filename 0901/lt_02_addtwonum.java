/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        ListNode* dummy = new ListNode(0); // 哨兵節點，方便返回
        ListNode* curr = dummy;
        int carry = 0;

        while (l1 || l2 || carry) {
            int sum = carry;
            if (l1) {
                sum += l1->val;
                l1 = l1->next;
            }
            if (l2) {
                sum += l2->val;
                l2 = l2->next;
            }

            carry = sum / 10; // 計算進位
            curr->next = new ListNode(sum % 10); // 當前節點值
            curr = curr->next;
        }

        return dummy->next;
    }

    /*
    解題思路：
    1. 逐位相加 l1 和 l2 的節點值，加上進位 carry。
    2. 使用 dummy 節點方便返回結果鏈表。
    3. 循環條件為 l1 或 l2 或 carry 不為零。
    4. 將 sum % 10 作為新節點值，sum / 10 更新進位 carry。
    5. 返回 dummy->next 作為結果鏈表。
    時間複雜度：O(max(m, n))，m 和 n 分別為兩個鏈表長度。
    空間複雜度：O(max(m, n))，用於存放結果鏈表。
    */
};