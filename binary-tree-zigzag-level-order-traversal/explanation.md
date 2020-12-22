## 二叉树的锯齿形层序遍历

[103. 二叉树的锯齿形层序遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)

#### 解题思路：二叉树的层序遍历

做本题前，需要我们熟知二叉树的层序遍历：

```java
public class TraversalTreeByLevelOrder{
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int value){
            this.value = value;
        }
    }
    
    public static void levelOrder(Node head){
        if(head != null){
            Queue<Node> queue = new LinkedList<>();
            queue.offer(head);
            while(!queue.isEmpty()){
                head = queue.poll();
                System.out.print(head.value + " ");
                if(head.left != null)
                    queue.offer(head.left);
                if(head.right != null)
                    queue.offer(head.right);
            }
        }
    }
}
```

本题的本质也是层序遍历二叉树，同样也需要使用队列这种数据结构；只不过，常规的层序遍历是从左向右遍历每一层；锯齿形遍历，则要求从左向右与从右向左的顺序依次迭代遍历二叉树的每一层。

我们使用一个布尔值来记录是从左向右遍历还是从右向左遍历该层：

```java
boolean isLeftToRight;
```

当其值为`true`则表示我们要从左向右遍历；当其值为`false`则表示我们要从右向左遍历

因为本题目返回的结果为`List<List<Integer>>` 类型；也就是说每一层的节点值都要存放在`List<Integer>` 中。

对应的存储方法：

- 当从左向右遍历的时候，我们就从前向后存储节点值：`list.add(node.val)`
- 当从右向左遍历的时候，我们就从后向前存储节点值：`list.add(0,node.val)`

剩下的部分就非常简单了，详见代码

#### 代码

*Java*

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root != null){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            boolean isLeftToRight = false;
            while(!queue.isEmpty()){
                int size = queue.size();
                isLeftToRight = !isLeftToRight;
                List<Integer> list = new ArrayList<>();
                for(int i = 0; i < size; i++){
                    root = queue.poll();
                    if(isLeftToRight){
                        list.add(root.val);
                    }else{
                        list.add(0,root.val);
                    }
                    
                    if(root.left != null){
                        queue.offer(root.left);
                    }
                    if(root.right != null){
                        queue.offer(root.right);
                    }
                }
                res.add(list);
            }
        }
        return res;
    }
}
```

