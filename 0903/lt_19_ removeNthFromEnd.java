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

#include <cstddef>
class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode dummy(0, head); // 使用虛擬頭，方便刪除頭節點
        ListNode* first = &dummy;
        ListNode* second = &dummy;

        // 讓 first 指標先走 n+1 步，保持 first 和 second 間距為 n
        for (int i = 0; i <= n; i++) {
            first = first->next;
        }

        // 同時移動 first 和 second，直到 first 到達尾部
        while (first) {
            first = first->next;
            second = second->next;
        }

        // second->next 是要刪除的節點
        ListNode* toDelete = second->next;
        second->next = second->next->next;
        delete toDelete; // 釋放記憶體

        return dummy.next;
    }

    /*
    解題思路：
    1. 使用 dummy 節點指向 head，方便刪除頭節點。
    2. 使用雙指標 first 和 second，first 先走 n+1 步。
    3. 同時移動 first 和 second，直到 first 到達尾部。
    4. 此時 second->next 即為倒數第 n 個節點，修改鏈結跳過它。
    5. 刪除該節點並返回 dummy.next。
    時間複雜度：O(sz)，遍歷鏈表一次。
    空間複雜度：O(1)，只使用常數指標。
    */
};