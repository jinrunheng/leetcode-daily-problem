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
        ListNode cur = head;
        while(cur != null){
            ListNode[] list = insertNode(head,cur);
            head = list[0];
            cur = list[1];
        }
        return head;
    }

    private static ListNode[] insertNode(ListNode head, ListNode cur) {
        if(cur == null){
            // 代表排序已经完成
            return new ListNode[]{head,null};
        }
        if(cur == head){
            return new ListNode[]{head,cur.next};
        }

        ListNode node = head;
        while(node.val < cur.val){
            node = node.next;
        }
        ListNode pre = head;
        while(pre != null && pre != node && pre.next != node){
            pre = pre.next;
        }
        if(node == cur){
            return new ListNode[]{head,cur.next};
        }

        if(pre == node){
            ListNode end = node;
            while(end.next != cur){
                end = end.next;
            }
            end.next = cur.next;
            cur.next = node;
            return new ListNode[]{cur,end.next};
        }

        ListNode next = cur.next;

        ListNode end = node;
        while(end.next != cur){
            end = end.next;
        }
        end.next = next;
        pre.next = cur;
        cur.next = node;
        return new ListNode[]{head,next};
    }

}