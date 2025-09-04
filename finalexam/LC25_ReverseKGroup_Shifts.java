import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int k = sc.nextInt();
        ListNode dummy = new ListNode(0), cur = dummy;
        while (sc.hasNextInt()) {
            cur.next = new ListNode(sc.nextInt());
            cur = cur.next;
        }
        ListNode head = reverseKGroup(dummy.next, k);
        printList(head);
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroupEnd = dummy;

        while (true) {
            ListNode kth = getKth(prevGroupEnd, k);
            if (kth == null) break; 
            ListNode groupStart = prevGroupEnd.next;
            ListNode nextGroupStart = kth.next;
            reverse(groupStart, kth);

            prevGroupEnd.next = kth;
            groupStart.next = nextGroupStart;

            prevGroupEnd = groupStart;
        }
        return dummy.next;
    }

    private static ListNode getKth(ListNode start, int k) {
        while (start != null && k > 0) {
            start = start.next;
            k--;
        }
        return start;
    }

    private static void reverse(ListNode start, ListNode end) {
        ListNode prev = end.next;
        ListNode cur = start;
        while (prev != end) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
    }

    private static void printList(ListNode head) {
        boolean first = true;
        while (head != null) {
            if (!first) System.out.print(" ");
            System.out.print(head.val);
            first = false;
            head = head.next;
        }
        if (!first) System.out.println();
    }
}
