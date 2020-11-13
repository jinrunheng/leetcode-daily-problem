## 奇偶链表

[328. 奇偶链表](https://leetcode-cn.com/problems/odd-even-linked-list/)

#### 思路：双指针

首先，如果传入的链表头节点为空，或者链表的长度小于等于2，就没有必要执行算法了，直接返回即可：

```java
if(head == null || head.next == null || head.next.next == null)
    return head
```

算法的思路为双指针，用一个指针标记奇数节点，使用另一个指针标记偶数节点，两个指针依次迭代，形成奇数链表和偶数链表；最后我们让odd指针指向的节点（即奇数链的尾部）指向偶数链的头部即可，所以我们还需要一个指针 `evenHead`维护偶数链的头部

```
     
     even
      ↓
  1 → 2 → 3 → 4 → 5 → NULL
  ↑   ↑
 odd  evenHead
```

实现整体思路为：

```java
while(odd != null && even != null){
    odd.next = even.next;
    odd = odd.next;
    
    even.next = odd.next;
    even = even.next;
}
```



细节部分：

当链表节点个数为奇数个时，链表最后会变为：

```
 		 o
 		 ↓
 1 → 3 → 5 → NULL
        ↗
 2 → 4
 ↑    ↑
 eH   e
```

如果`odd.next == null`，我们需要:

```java
even.next = null;
odd.next = evenHead;
```

这样一个奇偶链表就完成了,我们接下来返回链表头部`head`即可



当链表节点个数为偶数个时，链表最后会变为：

```
 	 o	 
 	 ↓	 
 1 → 3 → NULL
        
 2 → 4 → NULL
 ↑   ↑
 eH  e
```

如果`even.next == null`，我们需要:

```java
odd.next = evenHead;
```

这样一个奇偶链表就完成了,我们接下来返回链表头部`head`即可

#### 代码实现

Java:

```java
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
```

Go:

```go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func oddEvenList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil || head.Next.Next == nil{
		return head
	}
	odd := head
	even := head.Next
	evenHead := head.Next

	for odd != nil && even != nil {
		if even.Next == nil {
			break
		}
		odd.Next = even.Next
		odd = odd.Next
		if odd.Next == nil {
			even.Next = nil
			break
		}
		even.Next = odd.Next
		even = even.Next
	}
	odd.Next = evenHead
	return head
}
```

