class Solution {
    public void rotate(int[] nums, int k) {
        // 方法一:使用辅助数组
        int[] help = new int[nums.length];
        int i = 0;
        for(int j = nums.length - k % nums.length; j < nums.length; j++){
            help[i++] = nums[j];
        }
        for(int j = 0; j < nums.length - k % nums.length; j++){
            help[i++] = nums[j];
        }

        // 将help写回到原数组
        for(int j = 0; j < nums.length; j++){
            nums[j] = help[j];
        }
    }
}