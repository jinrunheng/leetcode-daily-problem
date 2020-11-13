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
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = head.next;
        while(odd != null && even != null){
            if(even.next == null){
                break;
            }
            
            odd.next = even.next;
            odd = odd.next;
            if(odd.next == null){
                even.next = null;
                break;
            }
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}