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