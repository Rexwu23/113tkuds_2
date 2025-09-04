/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;

        while (head != null && head.next != null) {
            ListNode a = head;        // 第 1 個
            ListNode b = head.next;   // 第 2 個

            // 重新連結：prev -> b -> a -> next
            prev.next = b;
            a.next = b.next;
            b.next = a;

            // 移動到下一組
            prev = a;
            head = a.next;
        }
        return dummy.next;
    }
}
