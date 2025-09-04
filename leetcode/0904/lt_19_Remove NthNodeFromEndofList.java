class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;

        // 先把 fast 推到距 slow 相差 n+1
        for (int i = 0; i <= n; i++) fast = fast.next;

        // 一起走到尾，此時 slow 在欲刪節點的前一個
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;   // 刪除
        return dummy.next;
    }
}
