## 快慢指针找中点，额外空间复杂度O(1)

本题链接：[https://leetcode-cn.com/problems/reorder-list/](https://leetcode-cn.com/problems/reorder-list/)

不使用额外的数据结构，利用快慢指针即可完成本题，具体思路如下:

**1.** 快慢指针找到中点

**2.** 翻转中点之后的链表

**3.** 完成实际的节点交换



例如对于链表**`1 ->2 ->3 ->2 ->1`**



*![image.png](https://pic.leetcode-cn.com/1603183609-mOQpcO-image.png)*



当快慢指针遍历到边界时，如上图所示；此时，将慢指针指向的节点及后面部分进行链表的反转，再让p1，p2分别指向链表节点的头部，如下图：



*![image.png](https://pic.leetcode-cn.com/1603183660-YqkcLg-image.png)*



以上部分的代码如下：

```java
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
```



接下来我们只需要完成实际的节点交换即可，为了防止混淆，我们再使用两个变量维护**`p1.next`**和**`p2.next`**这样思路就变得清晰很多：

```java
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
```





#### 完整代码如下：



```java
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
```

