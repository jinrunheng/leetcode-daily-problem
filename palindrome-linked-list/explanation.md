## 栈，快慢指针

#### 方法一：栈

回文结构的问题都可以使用栈这种数据结构解决

1. 首先从头至尾遍历一次链表，将链表每个节点值存放到栈中

2. 然后再次从头遍历链表，同时从栈中往外pop，并对比，如果不同则返回false

3. 最后返回栈是否为空

##### 代码如下：

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        
        if (head.next == null) {
            return true;
        }

        // 思路一：使用栈
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return stack.isEmpty();
    }
}
```

#### 方法二：快慢指针

因为在题目要求中，希望我们以空间复杂度O(1)来解决这道问题

快慢指针思想如下：

1. 快指针一次走两步，慢指针一次走一步；快指针走到链表末尾时，慢指针则走到中点
2. 翻转中点之后的链表结构
3. 然后将两个指针重置到各自的头部，并依次比对

例如对于链表`1 ->2 ->3 ->2 ->1`



![image.png](https://pic.leetcode-cn.com/1603183609-mOQpcO-image.png)



当快慢指针遍历到边界时，如上图所示；此时，将慢指针指向的节点及后面部分进行链表的反转，再让p1，p2分别指向链表节点的头部，如下图：



![image.png](https://pic.leetcode-cn.com/1603183660-YqkcLg-image.png)

然后，让p1和p2都从各自的链表头开始遍历，并依次比对，如果`p1.val != p2.val`则返回空，否则则让p1和p2都指向下一个节点，直至为空



##### 代码如下：

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        if (head.next == null) {
            return true;
        }
        if (head.next.next == null) {
            return head.val == head.next.val;
        }
        // 方法二：快慢指针
        ListNode p1 = head; // slow
        ListNode p2 = head; // fast
        while (p2.next != null && p2.next.next != null) {
            p2 = p2.next.next;
            p1 = p1.next;
        }
        // p3 = pre
        // p1 = cur
        // p2 = next
        ListNode p3 = null;
        while (p1 != null) {
            p2 = p1.next;
            p1.next = p3;
            p3 = p1;
            p1 = p2;
        }
        p1 = head;
        p2 = p3;
        p3 = null;
        while (p1 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}
```

