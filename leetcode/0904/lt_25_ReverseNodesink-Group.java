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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) return head;

        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy; // 每組前一個節點

        while (true) {
            // 找到這組的第 k 個節點（組尾）
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;          // 不足 k 個，結束
            ListNode groupNext = kth.next;   // 下一組的起點

            // 反轉 [groupPrev.next, kth] 這一段，尾端指向 groupNext
            ListNode prev = groupNext;
            ListNode cur = groupPrev.next;
            while (cur != groupNext) {
                ListNode nxt = cur.next;
                cur.next = prev;
                prev = cur;
                cur = nxt;
            }

            // 接回去：groupPrev -> (kth ... 原首) -> groupNext
            ListNode newGroupHead = kth;
            ListNode newGroupTail = groupPrev.next; // 反轉後的尾巴（原首）
            groupPrev.next = newGroupHead;
            groupPrev = newGroupTail;               // 移動到下一組前一個
        }
        return dummy.next;
    }

    // 從 curr 往後走 k 步，回傳到達的節點（不足 k 步回傳 null）
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
