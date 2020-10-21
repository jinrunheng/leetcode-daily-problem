class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null){
            return;
        }
        ListNode p1 = head; // slow
        ListNode p2 = head; // fast
        while(p2.next != null && p2.next.next != null){
            p1 = p1.next;
            p2 = p2.next.next;
        }
        // p3 -> pre
        // p1 -> cur
        // p2 -> next

        ListNode p3 = null;
        while(p1 != null){
            p2 = p1.next;
            p1.next = p3;
            p3 = p1;
            p1 = p2;
        }

        p1 = head;
        p2 = p3;
        p3 = null; // 手动将p3置空
        
        ListNode p1Next = null;
        ListNode p2Next = null;
        while(p1 != p2){
            // 当链表节点个数为偶数个时
            if(p1.next == null){
                p1.next = p2;
                p2.next = null;
                break;
            }
            p1Next = p1.next;
            p2Next = p2.next;
            p1.next = p2;
            p2.next = p1Next;
            p1 = p1Next;
            p2 = p2Next;
        }
    }
}