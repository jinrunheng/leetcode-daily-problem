/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            // 如果本来就有序
            if(pre.val <= cur.val){
                pre = cur;
                cur = cur.next;
            }else {
                ListNode node = dummyHead;
                while(node.next != cur && node.next.val < cur.val){
                    node = node.next;
                }
                // 将 cur 插入到 node和node.next之间
                pre.next = cur.next;
                cur.next = node.next;
                node.next = cur;
                cur = pre.next;
            }
        }
        return dummyHead.next;
    }
}