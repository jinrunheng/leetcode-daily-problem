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