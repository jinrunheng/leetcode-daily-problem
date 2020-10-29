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
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root,0);
    }

    private int sumNumbers(TreeNode root, int val){
        if(root == null){
            return 0;
        }
        int tmp = val * 10 + root.val;
        if(root.left == null && root.right == null){
            return tmp;
        }
        return sumNumbers(root.left,tmp) + sumNumbers(root.right,tmp);
    }
}