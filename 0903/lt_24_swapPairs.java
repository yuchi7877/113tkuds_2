/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x, next) {}
 * };
 */

class Solution {
public:
    ListNode* swapPairs(ListNode* head) {
        ListNode dummy(0, head); // 虛擬頭節點，方便操作
        ListNode* prev = &dummy;

        while (prev->next && prev->next->next) {
            ListNode* first = prev->next;
            ListNode* second = first->next;

            // 交換節點
            first->next = second->next;
            second->next = first;
            prev->next = second;

            // 移動 prev 指標到下一對節點前
            prev = first;
        }

        return dummy.next;
    }

    /*
    解題思路：
    1. 使用虛擬頭節點 dummy 指向 head，方便操作頭節點。
    2. 使用 prev 指標指向要交換的兩個節點的前一個節點。
    3. 對每一對節點 first、second：
       - first->next 指向 second->next
       - second->next 指向 first
       - prev->next 指向 second
    4. 將 prev 移動到 first（即交換後的第二個節點），繼續下一輪。
    5. 返回 dummy.next 作為新的頭節點。
    時間複雜度：O(n)，遍歷一次鏈表。
    空間複雜度：O(1)，只使用常數指標。
    */
};
