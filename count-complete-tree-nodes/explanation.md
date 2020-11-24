## 完全二叉树的节点个数

[222. 完全二叉树的节点个数](https://leetcode-cn.com/problems/count-complete-tree-nodes/)

#### 思路：递归

题目给定的条件为：完全二叉树

将问题分解：

case1:

![img](https://upload-images.jianshu.io/upload_images/16743411-03b419b2a6a60128.png?imageMogr2/auto-orient/strip|imageView2/2/w/471/format/webp)

当一棵完全二叉树的头节点的右子树高度等于总高度(h)-1时，头节点的左子树必为一棵满二叉树，头节点的左满二叉树的节点个数为`2^(h-1) -1`个; 

这棵树的总节点数为: 左子树的节点个数 + 头节点 + 右子树的节点个数

这棵树的总结点数的表达式为:`2^(h-1) -1 + 1 + countNodes(root.right)`



case2:

![img](https://upload-images.jianshu.io/upload_images/16743411-8d173bf702db28bc.png?imageMogr2/auto-orient/strip|imageView2/2/w/529/format/webp)

当一棵完全二叉树的头节点的右子树的高度等于总高度(h)-2时，头节点的右子树必为一棵满二叉树，头节点的右满二叉树的节点个数为`2^(h-2)-1`个;

这棵树的总节点数为: 左子树的节点个数 + 头节点 + 右子树的节点个数

这棵树的总结点数的表达式为:`countNodes(root.left) + 1 + 2^(h-2)-1`



#### 代码

```JAVA
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
    public int countNodes(TreeNode root) {
        // recursion
        if(root == null){
            return 0;
        }
        return countNodes(root,1,getHeight(root));
    }

    private static int countNodes(TreeNode node,int level,int h){
        if(level == h){
            return 1;
        }
        if(getHeight(node.right) + level == h){
            // 左子树为满二叉树
            return (1 << (h - level)) + countNodes(node.right,level + 1,h);
        }else {
            // 右子树为满二叉树
            return (1 << (h - level - 1)) + countNodes(node.left,level + 1,h);
        }
    }

    // 获取当前节点的高度
    private static int getHeight(TreeNode node){
        int h = 0;
        while(node != null){
            node = node.left;
            h++;
        }
        return h;
    }
}
```

