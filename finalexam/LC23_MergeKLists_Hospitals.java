import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int k = sc.nextInt();

        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            lists[i] = readListUntilSentinel(sc);
        }

        ListNode merged = mergeKLists(lists);
        printList(merged);
    }

    private static ListNode readListUntilSentinel(Scanner sc) {
        ListNode dummy = new ListNode(0), cur = dummy;
        while (sc.hasNextInt()) {
            int x = sc.nextInt();
            if (x == -1) break;     
            cur.next = new ListNode(x);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode head : lists) if (head != null) pq.offer(head);

        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
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
