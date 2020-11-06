/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        // hash 
        // dummyHead
        Set<Integer> set = new HashSet<>();
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        
        ListNode cur = dummyHead;
        while(cur.next != null){
            if(set.contains(cur.next.val)){
                if(cur.next.next != null){
                    cur.next = cur.next.next;
                }else {
                    cur.next = null;
                }
            }else {
                set.add(cur.next.val);
                cur = cur.next;
            }
        }
        return dummyHead.next;
    }       
}