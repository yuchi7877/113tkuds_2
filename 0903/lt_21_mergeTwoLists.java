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
    ListNode* mergeTwoLists(ListNode* list1, ListNode* list2) {
        ListNode dummy(0); // 虛擬頭節點，方便操作
        ListNode* tail = &dummy;

        while (list1 && list2) {
            if (list1->val < list2->val) {
                tail->next = list1;
                list1 = list1->next;
            } else {
                tail->next = list2;
                list2 = list2->next;
            }
            tail = tail->next;
        }

        // 將剩餘節點接到尾部
        if (list1) tail->next = list1;
        if (list2) tail->next = list2;

        return dummy.next;
    }

    /*
    解題思路：
    1. 建立虛擬頭節點 dummy，tail 指向當前合併鏈表尾部。
    2. 迴圈比較 list1 和 list2 的當前節點值：
       - 將較小節點接到 tail->next。
       - 移動該鏈表指標和 tail。
    3. 當其中一個鏈表遍歷完，將另一個鏈表剩餘部分接到 tail。
    4. 返回 dummy.next 作為合併後的頭節點。
    時間複雜度：O(m+n)，m, n 為兩個鏈表長度。
    空間複雜度：O(1)，只使用常數指標。
    */
};





