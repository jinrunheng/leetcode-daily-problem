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