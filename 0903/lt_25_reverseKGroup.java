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
    ListNode* reverseKGroup(ListNode* head, int k) {
        if (!head || k == 1) return head;

        ListNode dummy(0, head);
        ListNode* prevGroup = &dummy;
        ListNode* curr = head;

        int count = 0;
        while (curr) {
            count++;
            curr = curr->next;
        }

        curr = head;
        while (count >= k) {
            ListNode* prev = nullptr;
            ListNode* node = curr;
            ListNode* nextGroup = curr;
            for (int i = 0; i < k; i++) {
                ListNode* next = node->next;
                node->next = prev;
                prev = node;
                node = next;
            }
            // 連接上一組與下一組
            prevGroup->next = prev;
            nextGroup->next = node;

            // 移動指標到下一組
            prevGroup = nextGroup;
            curr = node;
            count -= k;
        }

        return dummy.next;
    }

    /*
    解題思路：
    1. 使用虛擬頭節點 dummy，方便操作。
    2. 計算鏈表長度 count。
    3. 當剩餘節點數 >= k：
       - 反轉當前 k 個節點：
         a. 使用 prev 指標反轉節點。
         b. node 指向當前節點，next 暫存下一個節點。
       - 反轉後將上一組尾節點連接到 prev（新頭）。
       - 將反轉後的尾節點連接到下一組。
    4. 移動 prevGroup 和 curr 指標到下一組。
    5. 返回 dummy.next。
    時間複雜度：O(n)，每個節點只被處理一次。
    空間複雜度：O(1)，使用常數指標。
    */
};
